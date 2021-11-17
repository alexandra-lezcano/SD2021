package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import com.protectionapp.sd2021.domain.denuncia.TipoSujetoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoSujetoDao extends CrudRepository<TipoSujetoDomain,Integer> {
    public Page<TipoSujetoDomain> findAll(Pageable pageable);
}
