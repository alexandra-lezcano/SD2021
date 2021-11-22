package com.protectionapp.sd2021.dao.casosDerivados;

import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface IDepEstadoDao extends JpaRepository<DepEstadoDomain, Integer> {

        public Page<DepEstadoDomain> findAll(Pageable pageable);



}
