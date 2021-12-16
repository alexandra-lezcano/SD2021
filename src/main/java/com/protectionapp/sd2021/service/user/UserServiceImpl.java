package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.casosDerivados.ICasosDerivadosDao;
import com.protectionapp.sd2021.dao.user.IRoleDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;
import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.RoleDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.service.casosDerivados.ICasosDerivadosService;
import com.protectionapp.sd2021.service.denuncia.IDenunciaService;
import com.protectionapp.sd2021.service.investigacion.InvestigacionServiceImpl;
import com.protectionapp.sd2021.service.location.ICityService;
import com.protectionapp.sd2021.service.location.INeighborhoodService;
import com.protectionapp.sd2021.utils.Configurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDomain, UserResult> implements IUserService {
    /*IOC - Inyeccion de Control evita que tenga que crear manualmente un UserDAO   */
    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICityService cityService;

    @Autowired
    private INeighborhoodService neighborhoodService;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private ICasosDerivadosDao casosDerivadosDao;

    @Autowired
    private IDenunciaService denunciaService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Configurations configurations;

    @Autowired
    private ICasosDerivadosService casosDerivadosService;

    @Autowired
    private InvestigacionServiceImpl investigacionService;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected UserDTO convertDomainToDto(UserDomain userDomain) {
        final UserDTO dto = new UserDTO();
        dto.setId(userDomain.getId());
        dto.setName(userDomain.getName());
        dto.setSurname(userDomain.getSurname());
        dto.setUsername(userDomain.getUsername());
        dto.setCn(userDomain.getCn());
        dto.setEmail(userDomain.getEmail());
        dto.setPhone(userDomain.getPhone());
        dto.setAddress(userDomain.getAddress());
        dto.setPassword(userDomain.getPassword());

        if (userDomain.getRoles() != null){
            Set<Integer> roles = new HashSet<Integer>();
            userDomain.getRoles().forEach(d->roles.add(d.getId()));
            dto.setRoleId(roles);
        }
        if (userDomain.getCity() != null) dto.setCityId(userDomain.getCity().getId());

        /* Relacion ManyToMany
        Necesito guardar una lista de ids de los barrios a mi user DTO */
        if (userDomain.getNeighborhoods() != null) {
            Set<Integer> neighborhoodIds = new HashSet<>();
            userDomain.getNeighborhoods().forEach(n_domain -> neighborhoodIds.add(n_domain.getId()));
            dto.setNeighborhoodIds(neighborhoodIds);
        }

        //relacion onetomany con casos derivados
        if(userDomain.getCasos_derivados()!=null){
            Set<Integer> casosIds = new HashSet<>();
            userDomain.getCasos_derivados().forEach(d ->casosIds.add(d.getId()));
            dto.setCasosDerivados(casosIds);
        }

        Set<Integer> investigacionIds = new HashSet<>();
        if(userDomain.getInvestigaciones()!=null){
            userDomain.getInvestigaciones().forEach(investigacionDomain -> investigacionIds.add(investigacionDomain.getId()));
        }
        dto.setInvestigacionIds(investigacionIds);
        return dto;
    }

    @Override
    protected UserDomain convertDtoToDomain(UserDTO dto) {
        final UserDomain userDomain = new UserDomain();
        userDomain.setId(dto.getId());
        userDomain.update(dto.getName(),dto.getSurname(),dto.getUsername(),dto.getCn(),dto.getAddress(),dto.getEmail(),dto.getPhone(),dto.getPassword());

        investigacionService.addInvestigacionesToUser(dto, userDomain);
        neighborhoodService.addNeighborhoodToUser(dto, userDomain); // transaction requires new - COMENZA ACA tal vez getAll falla porque esto requiere una nueva transaccion
        cityService.addCityToUser(dto, userDomain); // transaction not supported

        if (dto.getRoleId() != null) {
            Set<RoleDomain> roles= new HashSet<RoleDomain>();
            dto.getRoleId().forEach(d->roles.add(roleDao.findById(d).get()));
            userDomain.setRoles(roles);
          //  userDomain.setRole(roleDao.findById(dto.getRoleId()).get());
        }

        //relacion onetomany con casosDerivados

        if(dto.getCasosDerivados() !=null){
            Set<CasosDerivadosDomain> casos = new HashSet<>();
            dto.getCasosDerivados().forEach(d ->casos.add(casosDerivadosDao.findById(d).get()));
            userDomain.setCasos_derivados(casos);
        }

        return userDomain;
    }


    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        final UserDomain userDomain = convertDtoToDomain(dto);
        final UserDomain user = userDao.save(userDomain);

        if (dto.getId() == null) {
            Integer id = userDomain.getId();
            dto.setId(id);
            cacheManager.getCache(Configurations.CACHE_NOMBRE).put("api_user_" + id, dto);
        }
        return convertDomainToDto(user);
    }

    @Override
    @Transactional
 //   @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO getById(Integer id) {
        final UserDomain user = userDao.findById(id).get();
        return convertDomainToDto(user);
    }


    @Transactional
    public UserDTO getByUsername (String userame){
        final UserDomain user= userDao.findByUsername(userame);
        return convertDomainToDto(user);
    }

    /* findAll() tiene su propia transaccion, pero la transaccion del metodo en si no se crea
    o.s.t.s.AbstractPlatformTransactionManager:
    Creating new transaction with name [org.springframework.data.jpa.repository.support.SimpleJpaRepository.findAll]:
    PROPAGATION_REQUIRED,ISOLATION_DEFAULT,readOnly

    Si cambio a @Transactional el metodo getAll es el padre de la transaccion
    o.s.t.s.AbstractPlatformTransactionManager:
    Creating new transaction with name [com.protectionapp.sd2021.service.user.UserServiceImpl.getAll]:
    PROPAGATION_REQUIRED,ISOLATION_DEFAULT
    */

    @Override
    @Transactional
    public UserResult getAll(Pageable pageable) {
        final List<UserDTO> users = new ArrayList<>();
        Page<UserDomain> results = userDao.findAll(pageable);
        results.forEach(user -> users.add(convertDomainToDto(user)));

        final UserResult userResult = new UserResult();
        userResult.setUsers(users);
        return userResult;
    }

    public UserResult getllAllNotPaginated() {
        final UserResult result = new UserResult();
        final Iterable<UserDomain> allDomains = userDao.findAll();
        final List<UserDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(tipoDenunciaDomain -> allDtos.add(convertDomainToDto(tipoDenunciaDomain)));
        }

        result.setUsers(allDtos);
        return result;
    }

    @Override
    @Transactional
  //  @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO update(UserDTO dto, Integer id) {
        final UserDomain updatedUserDomain = userDao.findById(id).get();

        updatedUserDomain.update(
                dto.getName(),
                dto.getSurname(),
                dto.getUsername(),
                dto.getCn(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getPassword()
        );
/*
        if (dto.getRoleId() != null){
            //updatedUserDomain.setRole(roleDao.findById(dto.getRoleId()).get());
        }*/
        if (dto.getRoleId() != null) {
            Set<RoleDomain> roles= new HashSet<RoleDomain>();
            dto.getRoleId().forEach(d->roles.add(roleDao.findById(d).get()));
            updatedUserDomain.setRoles(roles);
            //  userDomain.setRole(roleDao.findById(dto.getRoleId()).get());
        }

        investigacionService.addInvestigacionesToUser(dto, updatedUserDomain);
        neighborhoodService.addNeighborhoodToUser(dto, updatedUserDomain); // transaction requires_new
        cityService.addCityToUser(dto, updatedUserDomain); // transaction not_supported
        userDao.save(updatedUserDomain);
        return convertDomainToDto(updatedUserDomain);
    }

    @Override
    @Transactional
   // @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO delete(Integer id) {
        final UserDomain deletedUserdomain = userDao.findById(id).get();
        final UserDTO deletedUserDto = convertDomainToDto(deletedUserdomain);
        userDao.delete(deletedUserdomain);
        return deletedUserDto;
    }

    @Override
    @Transactional
    public void addUsersToInvestigacion(InvestigacionDTO dto, InvestigacionDomain domain) {
        Set<UserDomain> userDomains = new HashSet<>();
        if(dto.getUserIds()!=null){
            dto.getUserIds().forEach(userId -> userDomains.add(userDao.findById(userId).get()));
        }
        domain.setUsers(userDomains);
    }

    @Override
    @Transactional
    //   @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDomain getDomainById(Integer id) {
        final UserDomain user = userDao.findById(id).get();
        return user;
    }

}