package com.protectionapp.sd2021.dao.user;

import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* TODO TESTEAR CON JPA REPOSITORY*/
@Repository
public interface IUserDao extends JpaRepository<UserDomain, Integer> {
    // Permite retornar results paginados
    public Page<UserDomain> findAll(Pageable pageable);
}
