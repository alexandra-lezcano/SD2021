package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.RoleDTO;
import com.protectionapp.sd2021.service.location.CityServiceImpl;
import com.protectionapp.sd2021.service.user.IRoleService;
import com.protectionapp.sd2021.service.user.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleResource {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/{id}")
    @ResponseBody

    public RoleDTO getById(@PathVariable(value = "id") Integer cityId) {
        return roleService.getById(cityId);
    }

}
