package com.protectionapp.sd2021.dao.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.CasosDerivadosDomain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICasosDerivadosDao extends CrudRepository<CasosDerivadosDomain, Integer> {
    public Page<CasosDerivadosDomain> findAll(Pageable pageable);

}
