package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ITipoDenunciaDao extends CrudRepository<TipoDenunciaDomain, Integer> {
    public Page<TipoDenunciaDomain> findAll(Pageable pageable);
}
