package com.protectionapp.sd2021.dao.location;

import com.protectionapp.sd2021.domain.location.NeighborhoodDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface INeighborhoodDao extends CrudRepository<NeighborhoodDomain, Integer> {
    // Permite retornar results paginados
    public Page<NeighborhoodDomain> findAll(Pageable pageable);

}
