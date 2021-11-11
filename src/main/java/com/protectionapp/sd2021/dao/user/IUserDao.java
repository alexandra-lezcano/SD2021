package com.protectionapp.sd2021.dao.user;

import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* Realiza transacciones con el Bean de usuarios cuya clave es un Integer
 * CrudRepository se encarga de todas las implementaciones correspondientes
 * Metodos transaccionales no necesitan usar save cuando hay un update*/
@Repository
public interface IUserDao extends CrudRepository<UserDomain, Integer> {
    // Permite retornar results paginados
    public Page<UserDomain> findAll(Pageable pageable);
}
