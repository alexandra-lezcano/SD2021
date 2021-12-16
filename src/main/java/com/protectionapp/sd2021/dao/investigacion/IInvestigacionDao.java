package com.protectionapp.sd2021.dao.investigacion;

import com.protectionapp.sd2021.domain.investigacion.InvestigacionDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvestigacionDao extends JpaRepository<InvestigacionDomain, Integer> {
    Page<InvestigacionDomain> findAll(Pageable pageable);
}
