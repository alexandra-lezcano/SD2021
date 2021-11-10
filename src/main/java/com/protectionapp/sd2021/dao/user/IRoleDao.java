package com.protectionapp.sd2021.dao.user;

import com.protectionapp.sd2021.domain.user.RoleDomain;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends CrudRepository<RoleDomain, Integer> {


}
