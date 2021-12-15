package com.protectionapp.sd2021.dao.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;

import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICasosDerivadosDao extends CrudRepository<CasosDerivadosDomain, Integer> {
    public Page<CasosDerivadosDomain> findAll(Pageable pageable);


    Page<CasosDerivadosDomain> findByUser_idEquals(UserDomain user_id, Pageable pageable);

    Page<CasosDerivadosDomain> findByUser_IdEquals(Integer id, Pageable pageable);











}
