package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.service.location.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")

public class CityResource {
    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/{id}")
    @ResponseBody
    public CityDTO getById(@PathVariable(value = "id") Integer cityId) {
        return cityService.getById(cityId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CityDTO save(@Valid @RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDTO updateCity(@Valid @RequestBody CityDTO cityDTO, @PathVariable(value = "id") Integer id) {
        return cityService.update(cityDTO, id);
    }


}
