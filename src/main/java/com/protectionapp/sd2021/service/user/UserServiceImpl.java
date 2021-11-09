package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.IUserDao;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional; // para todos los metodos que van a la db
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected UserDTO convertDomainToDto(UserDomain domain) {
        final UserDTO user = new UserDTO();
        user.setName(domain.getName());
        user.setSurname(domain.getSurname());
        user.setUsername(domain.getUsername());
        user.setCn(domain.getCn());
        user.setEmail(domain.getEmail());
        user.setPhone(domain.getPhone());
        user.setAddress(domain.getAddress());

        return user;
    }

    @Override
    protected UserDomain convertDtoToDomain(UserDTO dto) {
        final UserDomain user = new UserDomain();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setUsername(dto.getUsername());
        user.setCn(dto.getCn());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());

        return user;
    }

    /**
     * Metodos que vienen de IBaseService
     **/

    /* Obtener el bean de user a traves del metodo convertDtoToDomain
     * Delegar al UserDao (@Autowired) la tarea de guardar el bean en la DB
     * Retornar un DTO guardado en la DB
     * ** para que retornamos el objeto que acabamos de recibir? ** */
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
        final List<UserDTO> users = new ArrayList<UserDTO>();
        Page<UserDomain> results = userDao.findAll(pageable);
        results.forEach(user -> users.add(convertDomainToDto(user)));

        final UserResult userResult = new UserResult();
        userResult.setUsers(users);
        return userResult;
    }
}
