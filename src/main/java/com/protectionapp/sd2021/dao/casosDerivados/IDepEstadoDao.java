package com.protectionapp.sd2021.dao.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface IDepEstadoDao extends CrudRepository<DepEstadoDomain, Integer> {

        public Page<DepEstadoDomain> findAll(Pageable pageable);

        Page<DepEstadoDomain> findByNameContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String name, String description, Pageable pageable);



}
