package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.location.NeighborhoodServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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

    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public NeighborhoodResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return neighborhoodService.getAll(PageRequest.of(pageNum, 5));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )
    public NeighborhoodResult getllAllNotPaginated() {
        return neighborhoodService.getllAllNotPaginated();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public NeighborhoodDTO save(@Valid @RequestBody NeighborhoodDTO neighborhoodDTO) {
        System.out.println(neighborhoodDTO);
        return neighborhoodService.save(neighborhoodDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public NeighborhoodDTO update(@Valid @RequestBody NeighborhoodDTO neighborhoodDTO, @PathVariable(value = "id") Integer id) {
        return neighborhoodService.update(neighborhoodDTO, id);
    }

    @DeleteMapping("/{id}")
    public NeighborhoodDTO delete(@PathVariable(value = "id") Integer id) {
        return neighborhoodService.delete(id);
    }


}
