package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.DenunciaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDenunciaDao extends CrudRepository<DenunciaDomain, Integer>{
    public Page<DenunciaDomain> findAll(Pageable pageable);
}
