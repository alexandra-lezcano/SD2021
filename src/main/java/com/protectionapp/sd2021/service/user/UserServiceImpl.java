package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.location.ICityDao;
import com.protectionapp.sd2021.dao.location.INeighborhoodDao;
import com.protectionapp.sd2021.dao.user.IRoleDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    protected UserDTO convertDomainToDto(UserDomain userDomain) {
        final UserDTO user = new UserDTO();
        user.setName(userDomain.getName());
        user.setSurname(userDomain.getSurname());
        user.setUsername(userDomain.getUsername());
        user.setCn(userDomain.getCn());
        user.setEmail(userDomain.getEmail());
        user.setPhone(userDomain.getPhone());
        user.setAddress(userDomain.getAddress());

        /* Relacion ManyToOne */
        if (userDomain.getCity() != null) {
            Integer city_id = userDomain.getCity().getId();
            user.setCityId(city_id);
        }

        /* Relacion ManyToMany
        Necesito guardar una lista de ids de los barrios a mi user DTO */
        if (userDomain.getNeighborhoods() != null) {
            user.setNeighborhoods(getNeighborhoodIdsFromDomain(userDomain));
        }

        return user;
    }

    @Override
    protected UserDomain convertDtoToDomain(UserDTO userDTO) {
        final UserDomain userDomain = new UserDomain();
       /* userDomain.setName(userDTO.getName());
        userDomain.setSurname(userDTO.getSurname());
        userDomain.setUsername(userDTO.getUsername());
        userDomain.setCn(userDTO.getCn());
        userDomain.setEmail(userDTO.getEmail());
        userDomain.setPhone(userDTO.getPhone());
        userDomain.setAddress(userDTO.getAddress());*/
        userDomain.update(
                userDTO.getName(),
                userDTO.getSurname(),
                userDTO.getUsername(),
                userDTO.getCn(),
                userDTO.getAddress(),
                userDTO.getEmail(),
                userDTO.getPhone());

        if (userDTO.getCityId() != null) {
            userDomain.setCity(cityDao.findById(userDTO.getCityId()).get());
        }
        if (userDTO.getNeighborhoodIds() != null) {
            userDomain.setNeighborhoods(getNeighborhoodDomainsFromDTO(userDTO));
        }
        return userDomain;
    }

    /**
     * Metodos que vienen de IBaseService
     **/

    /* Obtener el bean de user a traves del metodo convertDtoToDomain
     * Delegar al UserDao (@Autowired) la tarea de guardar el bean en la DB
     * Retornar un DTO guardado en la DB
     * ** Los response retornan dto ** */
    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        final UserDomain userDomain = convertDtoToDomain(dto);
        final UserDomain user = userDao.save(userDomain);

        return convertDomainToDto(user);
    }

    /* userDao (@CrudRepository) nos devuelve un bean buscando por id
     * Recordar que los daos son la capa encargada de realizar transacciones
     * con la DB y que CrudRepository ya implementa todos los metodos
     * necesarios para realizar transacciones */
    @Override
    @Transactional
    public UserDTO getById(Integer id) {
        final UserDomain user = userDao.findById(id).get();
        return convertDomainToDto(user);
    }

    /* Pageable es un objeto de spring que nos ayuda al hacer paginacion
     * Mirar UserResource.getAll(page_num) para que esto tenga sentido. Tambien
     * ver el objeto de Spring llamado PageRequest, esto esta relacionado a
     * JPA(Hibernate)
     *
     * Pageables tiene: qué número de pagina quiero mostrar y cuántos objetos
     * deben ser motrados en esa página. Entonces el DAO sabe cuántos objetos
     * más deben ser traidos desde la DB, y asi .findAll(pageable) retorna
     * una *Pagina* con todos los Domains necesarios. Despues nosotros recorremos
     * la *Pagina* de Domains, los convertimos a DTOs y respondemos con un
     * UserResult.
     *
     * El CLIENTE va a consumir ese UserResult cada vez que el usuario use
     * paginacion
     * */
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

    @Override
    @Transactional
    public UserDTO update(UserDTO dto, Integer id) {
        final UserDomain updatedUserDomain = userDao.findById(id).get();

        if (dto.getNeighborhoodIds() != null) {
            updatedUserDomain.setNeighborhoods(getNeighborhoodDomainsFromDTO(dto));
        }

        if (dto.getId() != null) {
            updatedUserDomain.setCity(cityDao.findById(dto.getCityId()).get());
        }

        if (dto.getRoleId() != null) {
            updatedUserDomain.setRole(roleDao.findById(dto.getRoleId()).get());
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

        userDao.save(updatedUserDomain);
        return convertDomainToDto(updatedUserDomain);
    }

    @Override
    public UserDTO delete(Integer id) {
        return null;
    }

    public Set<NeighborhoodDomain> getNeighborhoodDomainsFromDTO(UserDTO dto) {
        Set<NeighborhoodDomain> neighborhoodDomains = new HashSet<>();
        dto.getNeighborhoodIds().forEach(n_id -> neighborhoodDomains.add(neighborhoodDao.findById(n_id).get()));
        return neighborhoodDomains;
    }

    public Set<Integer> getNeighborhoodIdsFromDomain(UserDomain domain) {
        Set<Integer> neighborhood_ids = new HashSet<>();
        domain.getNeighborhoods().forEach(n_domain -> neighborhood_ids.add(n_domain.getId()));
        return neighborhood_ids;
    }
}