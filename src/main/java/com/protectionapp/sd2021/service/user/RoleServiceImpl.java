package com.protectionapp.sd2021.service.user;

import com.protectionapp.sd2021.dao.user.IRoleDao;
import com.protectionapp.sd2021.dao.user.IUserDao;
import com.protectionapp.sd2021.domain.user.RoleDomain;
import com.protectionapp.sd2021.domain.user.UserDomain;
import com.protectionapp.sd2021.dto.user.RoleDTO;
import com.protectionapp.sd2021.dto.user.RoleResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.base.BaseServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDomain,RoleResult> implements IRoleService {
    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IUserDao userDao;

    @Override
    protected RoleDTO convertDomainToDto(RoleDomain domain) {
        RoleDTO dto= new RoleDTO();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        Set<Integer> users = new HashSet<Integer>();
        if(domain.getUsers()!=null){
            domain.getUsers().forEach(d ->users.add(d.getId()));
            dto.setUsers(users);
        }

        return dto;

    }

    @Override
    protected RoleDomain convertDtoToDomain(RoleDTO dto) {
        RoleDomain roleDomain = new RoleDomain();
        roleDomain.setId(dto.getId());
        roleDomain.setName(dto.getName());
        roleDomain.setDescription(dto.getDescription());
        Set<UserDomain> users = new HashSet<UserDomain>();
        if(dto.getUsers()!=null){
            dto.getUsers().forEach(d->users.add(userDao.findById(d).get()));
            roleDomain.setUsers(users);
        }

        return roleDomain;
    }

    @Override
    public RoleDTO save(RoleDTO dto) {

        final RoleDomain roleDomain = convertDtoToDomain(dto);
        final RoleDomain role = roleDao.save(roleDomain);

        return convertDomainToDto(role);
    }

    @Override
    public RoleDTO getById(Integer id) {
        final RoleDomain role = roleDao.findById(id).get();
        return convertDomainToDto(role);

    }

    @Override
    public RoleResult getAll(Pageable pageable) {
        final List<RoleDTO> role = new ArrayList<>();
       // Page<RoleDomain> results = roleDao.findAll(pageable);
     // results.forEach(rol ->role.add(convertDomainToDto(rol)));

        final RoleResult roleResult = new RoleResult();
        roleResult.setRole(role);
        return roleResult;
    }

    @Override
    public RoleDTO update(RoleDTO dto, Integer id) {
        return null;
    }

    @Override
    public RoleDTO delete(Integer id) {
        return null;
    }


    public RoleResult getllAllNotPaginated() {
        final RoleResult result = new RoleResult();
        final Iterable<RoleDomain> allDomains = roleDao.findAll();
        final List<RoleDTO> allDtos = new ArrayList<>();

        result.setRole(allDtos);
        return result;
    }
}
