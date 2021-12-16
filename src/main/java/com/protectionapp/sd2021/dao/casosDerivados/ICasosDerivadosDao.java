package com.protectionapp.sd2021.dao.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;

import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ICasosDerivadosDao extends JpaRepository<CasosDerivadosDomain, Integer> {
    public Page<CasosDerivadosDomain> findAll(Pageable pageable);


    Page<CasosDerivadosDomain> findByUser_idEquals(UserDomain user_id, Pageable pageable);

    Page<CasosDerivadosDomain> findByUser_IdEquals(Integer id, Pageable pageable);











}
