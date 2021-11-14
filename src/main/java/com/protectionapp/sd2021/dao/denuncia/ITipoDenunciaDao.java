package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.TipoDenunciaDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoDenunciaDao extends CrudRepository<TipoDenunciaDomain, Integer> {
}
