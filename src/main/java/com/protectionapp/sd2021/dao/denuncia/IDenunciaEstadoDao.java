package com.protectionapp.sd2021.dao.denuncia;

import com.protectionapp.sd2021.domain.denuncia.DenunciaEstadoDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDenunciaEstadoDao extends CrudRepository<DenunciaEstadoDomain, Integer> {
}
