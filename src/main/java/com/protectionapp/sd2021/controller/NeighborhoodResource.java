package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.location.NeighborhoodServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
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
    Configurations configurations;

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
        return neighborhoodService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @ResponseBody
    public NeighborhoodResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value="size") Integer size ) {
        return neighborhoodService.getAll(PageRequest.of(pageNum, size));
    }

    @GetMapping(path = "page")
    @ResponseBody
    public NeighborhoodResult getAll() {
        return neighborhoodService.getAll(PageRequest.of(0, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "/find/{page}/{city}")
    @ResponseBody
    public NeighborhoodResult getByCityPaged(@PathVariable(value="page")Integer page, @PathVariable(value = "city") Integer city){
        System.out.println("City: " + city + " Page: " + page);
        return neighborhoodService.findAllByCityPaged(city, PageRequest.of(page, configurations.getItemsPaginacion()));
    }

    @GetMapping(path="res/{page}/{search}")
    public NeighborhoodResult getAllByName(@PathVariable(value = "page") Integer page, @PathVariable(value="search") String search){
        return neighborhoodService.getAllByName(PageRequest.of(page, configurations.getItemsPaginacion()), search);
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
