package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.CityResult;
import com.protectionapp.sd2021.dto.localization.NeighborhoodResult;
import com.protectionapp.sd2021.service.location.CityServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cities")

public class CityResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private CityServiceImpl cityService;

    @GetMapping("/{id}")
    @ResponseBody

    public CityDTO getById(@PathVariable(value = "id") Integer cityId) {
        return cityService.getById(cityId);
    }

    @GetMapping(path = "/page")
    @ResponseBody
    public CityResult getAll(){
        return cityService.getAll(PageRequest.of(0, configurations.getItemsPaginacion()));

    }

    @GetMapping(path = "/page/{page_num}")
    @ResponseBody

    public CityResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return cityService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "/page/{page_num}/{size}")
    @ResponseBody
    public CityResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value="size") Integer size) {
        return cityService.getAll(PageRequest.of(pageNum, size));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )

    public CityResult getllAllNotPaginated() {
        return cityService.getllAllNotPaginated();
    }

    @GetMapping(path="find/{page}/{search}")
    @ResponseBody
    public CityResult getByName(@PathVariable(value = "page") Integer page, @PathVariable(value="search") String search){
        return cityService.getAllByName(PageRequest.of(page, configurations.getItemsPaginacion()), search);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CityDTO save(@Valid @RequestBody CityDTO cityDTO) {
        return cityService.save(cityDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CityDTO update(@Valid @RequestBody CityDTO cityDTO, @PathVariable(value = "id") Integer id) {
        return cityService.update(cityDTO, id);
    }

    @DeleteMapping("/{id}")
    public CityDTO delete(@PathVariable(value = "id") Integer id) {
        return cityService.delete(id);
    }

    @GetMapping("/{id}/neighborhoods")
    @ResponseBody
    public NeighborhoodResult getNeighborhoods(@PathVariable(value = "id") Integer cityId) {
        return cityService.getNeighborhoodByCityId(cityId);
    }

}
