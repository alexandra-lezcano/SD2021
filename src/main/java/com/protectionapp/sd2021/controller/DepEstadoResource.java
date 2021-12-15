package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.casosDerivados.DepEstadoServiceImpl;

import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/depEstado")
public class DepEstadoResource {
    @Autowired
    private DepEstadoServiceImpl dEService;

    @Autowired
    Configurations configurations;

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoDTO getById(@PathVariable(value = "id") Integer DepEstadoId) {
        return dEService.getById(DepEstadoId);
    }


    @GetMapping(path = "page/{page_num}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return dEService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "/page")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoResult getAll(Pageable page) {
        return dEService.getAll(page);
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value="size") Integer size) {
        return dEService.getAll(PageRequest.of(pageNum, size));
    }

    @GetMapping(path="/find/{page}/{strtofind}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoResult getAllByName(@PathVariable(value="page") Integer page, @PathVariable(value="strtofind") String string){
        return dEService.getAllByName(PageRequest.of(page, configurations.getItemsPaginacion()),string);
    }

    @PostMapping()
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoDTO save(@Valid @RequestBody DepEstadoDTO dEDto) {
        return dEService.save(dEDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoDTO update(@RequestBody DepEstadoDTO dto, @PathVariable(value = "id") Integer id) {
        return dEService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DepEstadoDTO delete(@PathVariable(value = "id") Integer id) {
        return dEService.delete(id);
    }
}




