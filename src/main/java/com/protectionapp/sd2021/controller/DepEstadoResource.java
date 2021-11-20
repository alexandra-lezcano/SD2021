package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;

import com.protectionapp.sd2021.service.casosDerivados.DepEstadoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @PostMapping()
    public DepEstadoDTO save(@Valid @RequestBody DepEstadoDTO dEDto) {
        return dEService.save(dEDto);
    }

}