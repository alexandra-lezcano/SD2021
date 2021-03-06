package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.SujetoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISujetoDao extends CrudRepository<SujetoDomain, Integer> {
    public Page<SujetoDomain> findAll(Pageable pageable);
}
