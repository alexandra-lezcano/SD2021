package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.casosDerivados.DepEstadoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/depEstado")
public class DepEstadoResource {
    @Autowired
    private DepEstadoServiceImpl dEService;

    @GetMapping("/{id}")
    public DepEstadoDTO getById(@PathVariable(value = "id") Integer DepEstadoId) {
        return dEService.getById(DepEstadoId);
    }


    @GetMapping(path = "page/{page_num}")
    public DepEstadoResult getDepEstado(@PathVariable(value = "page_num") Integer pageNum) {
        return dEService.getAll(PageRequest.of(pageNum, 5));
    }

    @GetMapping(path = "/")
    public DepEstadoResult getAll(Pageable page) {
        return dEService.getAll(page);
    }


    @PostMapping()
    public DepEstadoDTO save(@Valid @RequestBody DepEstadoDTO dEDto) {
        return dEService.save(dEDto);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepEstadoDTO update(@RequestBody DepEstadoDTO dto, @PathVariable(value = "id") Integer id) {
        return dEService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public DepEstadoDTO delete(@PathVariable(value = "id") Integer id) {
        return dEService.delete(id);
    }
}




