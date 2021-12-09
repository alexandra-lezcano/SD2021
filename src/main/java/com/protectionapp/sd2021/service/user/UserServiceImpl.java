package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.user.IRoleDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.service.denuncia.IDenunciaService;
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

import org.springframework.transaction.annotation.Propagation;
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
    private IDenunciaService denunciaService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private Configurations configurations;

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
        userDomain.update(dto.getName(),dto.getSurname(),dto.getUsername(),dto.getCn(),dto.getAddress(),dto.getEmail(),dto.getPhone());

        neighborhoodService.addNeighborhoodToUser(dto, userDomain); // transaction requires new - COMENZA ACA tal vez getAll falla porque esto requiere una nueva transaccion
        cityService.addCityToUser(dto, userDomain); // transaction not supported
        denunciaService.addDenunciaToUser(dto, userDomain); // transaction mandatory

        if (dto.getRoleId() != null) userDomain.setRole(roleDao.findById(dto.getRoleId()).get());

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

        neighborhoodService.addNeighborhoodToUser(dto, updatedUserDomain); // transaction requires_new
        cityService.addCityToUser(dto, updatedUserDomain); // transaction not_supported
        denunciaService.addDenunciaToUser(dto, updatedUserDomain); // transaction mandatory

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


    @Override
    @Transactional
    public void rollbackPropagationNever(UserDTO userDTO, CityDTO cityDTO) {
        userDTO.setCityId(cityDTO.getId());
        save(userDTO);

        if(configurations.isTransactionTest()){
            logger.info("[TEST] Propagation.NEVER will rollback");
            final CityDTO city = cityService.update(cityDTO,cityDTO.getId()); // Propagation.NEVER
            logger.info("[TEST] check user is not saved");
        }
    }

    @Override
    public void methodCallPropagationNever(UserDTO userDTO, CityDTO cityDTO) {
        logger.info("[TEST] Propagation.NEVER - guardar usuario");
        userDTO.setCityId(cityDTO.getId());
        save(userDTO);

        logger.info("[TEST] Propagation.NEVER - actualizar ciudad");
        logger.info("[TEST] Propagation.NEVER - no participa en ninguna transaccion");
        final CityDTO city = cityService.update(cityDTO,cityDTO.getId()); // Propagation.NEVER
        logger.info("[TEST] check usuario y ciudad se guardan");
    }
}