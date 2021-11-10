package com.protectionapp.sd2021.dao.location;

import com.protectionapp.sd2021.domain.location.CityDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityDao extends CrudRepository<CityDomain, Integer> {
}
