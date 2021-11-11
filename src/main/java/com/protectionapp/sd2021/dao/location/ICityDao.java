package com.protectionapp.sd2021.dao.location;

import com.protectionapp.sd2021.domain.location.CityDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityDao extends CrudRepository<CityDomain, Integer> {
    // Permite retornar results paginados en CityServiceImpl
    public Page<CityDomain> findAll(Pageable pageable);
}
