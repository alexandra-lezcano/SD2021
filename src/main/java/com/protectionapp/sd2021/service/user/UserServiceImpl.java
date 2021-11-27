package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.dao.user.IRoleDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional; // para todos los metodos que van a la db
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* De arriba para abajo: el servicio recibe DTO y genera un DAO para la db
 * De abajo para arriba: el servicio recibe DAO y genera DTO para responder un request
 * * "arriba": requests que vienen de internet al controller
 * * "abajo": la base de datos
 *
 * Los dtos (@XmlRootElement) son objetos seriabliables que los controllers
 * envian y reciben por la web
 *
 * Los daos (@CrudRepository) son los encargados de realizar transacciones con la DB
 *
 * Los domains (@Entity) son representaciones de tablas en la DB
 * */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDomain, UserResult> implements IUserService {
    /*IOC - Inyeccion de Control evita que tenga que crear manualmente un UserDAO   */
    @Autowired
    private IUserDao userDao;

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private INeighborhoodDao neighborhoodDao;

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IDenunciaDao denunciaDao;

    @Autowired
    private CacheManager cacheManager;

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

        if (userDomain.getRole() != null) dto.setRoleId(userDomain.getRole().getId());
        if (userDomain.getCity() != null) dto.setCityId(userDomain.getCity().getId());

        /* Relacion ManyToMany
        Necesito guardar una lista de ids de los barrios a mi user DTO */
        if (userDomain.getNeighborhoods() != null) {
            Set<Integer> neighborhoodIds = new HashSet<>();
            userDomain.getNeighborhoods().forEach(n_domain -> neighborhoodIds.add(n_domain.getId()));
            dto.setNeighborhoodIds(neighborhoodIds);
        }

        if (userDomain.getDenuncias() != null) {
            Set<Integer> denunciasIds = new HashSet<>();
            userDomain.getDenuncias().forEach(d_domain -> denunciasIds.add(d_domain.getId()));
            dto.setDenunciasIds(denunciasIds);
        }

        return dto;
    }

    @Override
    protected UserDomain convertDtoToDomain(UserDTO dto) {
        final UserDomain userDomain = new UserDomain();

        userDomain.setId(dto.getId());
        userDomain.update(
                dto.getName(),
                dto.getSurname(),
                dto.getUsername(),
                dto.getCn(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPhone()
        );

        if (dto.getRoleId() != null) userDomain.setRole(roleDao.findById(dto.getRoleId()).get());
        if (dto.getCityId() != null) userDomain.setCity(cityDao.findById(dto.getCityId()).get());


        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        if (dto.getNeighborhoodIds() != null) {
            dto.getNeighborhoodIds().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        }
        userDomain.setNeighborhoods(neighborhoodDomains);

        Set<DenunciaDomain> denunciaDomains = new HashSet<>();
        if (dto.getDenunciasIds() != null) {
            dto.getDenunciasIds().forEach(d_id -> denunciaDomains.add(denunciaDao.findById(d_id).get()));
        }
        userDomain.setDenuncias(denunciaDomains);

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
    @Cacheable(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO getById(Integer id) {
        final UserDomain user = userDao.findById(id).get();
        return convertDomainToDto(user);
    }

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
        System.out.println("[ITERABLE] ALL DOMAINS " + allDomains.toString());
        final List<UserDTO> allDtos = new ArrayList<>();

        if (allDomains != null) {
            allDomains.forEach(tipoDenunciaDomain -> allDtos.add(convertDomainToDto(tipoDenunciaDomain)));
        }

        result.setUsers(allDtos);
        return result;
    }

    @Override
    @Transactional
    @CachePut(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO update(UserDTO dto, Integer id) {
        final UserDomain updatedUserDomain = userDao.findById(id).get();

        if (dto.getNeighborhoodIds() != null) {
            updatedUserDomain.setNeighborhoods(getNeighborhoodDomainsFromDTO(dto));
        }
        updatedUserDomain.update(
                dto.getName(),
                dto.getSurname(),
                dto.getUsername(),
                dto.getCn(),
                dto.getAddress(),
                dto.getEmail(),
                dto.getPhone()
        );

        if (dto.getRoleId() != null) updatedUserDomain.setRole(roleDao.findById(dto.getRoleId()).get());
        if (dto.getCityId() != null) updatedUserDomain.setCity(cityDao.findById(dto.getCityId()).get());

        userDao.save(updatedUserDomain);
        return convertDomainToDto(updatedUserDomain);
    }

    @Override
    @Transactional
    @CacheEvict(value = Configurations.CACHE_NOMBRE, key = "'api_user_'+#id")
    public UserDTO delete(Integer id) {
        final UserDomain deletedUserdomain = userDao.findById(id).get();
        final UserDTO deletedUserDto = convertDomainToDto(deletedUserdomain);
        userDao.delete(deletedUserdomain);
        return deletedUserDto;
    }

    @Transactional
    public Set<NeighborhoodDomain> getNeighborhoodDomainsFromDTO(UserDTO dto) {
        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        dto.getNeighborhoodIds().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        return neighborhoodDomains;
    }
}