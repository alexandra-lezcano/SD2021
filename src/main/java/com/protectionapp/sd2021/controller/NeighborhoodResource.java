package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.service.location.NeighborhoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/neighborhoods")
public class NeighborhoodResource {
    @Autowired
    private NeighborhoodServiceImpl neighborhoodService;

    @GetMapping("/{id}")
    @ResponseBody
    public NeighborhoodDTO getById(@PathVariable(value = "id") Integer cityId) {
        return neighborhoodService.getById(cityId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public NeighborhoodDTO save(@Valid @RequestBody NeighborhoodDTO neighborhoodDTO) {
        return neighborhoodService.save(neighborhoodDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NeighborhoodDTO updateCity(@Valid @RequestBody NeighborhoodDTO neighborhoodDTO, @PathVariable(value = "id") Integer id) {
        return neighborhoodService.update(neighborhoodDTO, id);
    }

}
