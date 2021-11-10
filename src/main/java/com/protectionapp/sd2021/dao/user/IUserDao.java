package com.protectionapp.sd2021.dao.user;

import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* Realiza transacciones con el Bean de usuarios cuya clave es un Integer
 * CrudRepository se encarga de todas las implementaciones correspondientes*/
@Repository
public interface IUserDao extends CrudRepository<UserDomain, Integer> {
    /* Que es un rg.springframework.data.domain.Page ?
     * Aldo agrego un metodo que devuelve todos los clientes, pero donde es
     * implementado eso? */
    public Page<UserDomain> findAll(Pageable pageable);
}
