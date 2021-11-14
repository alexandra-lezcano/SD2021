package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaEstadoDTO;
import com.protectionapp.sd2021.service.denuncia.DenunciaEstadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/denunciaEstados")
public class DenunciaEstadoResource {

    @Autowired
    private DenunciaEstadoServiceImpl estadoService;

    @GetMapping("/{id}")
    @ResponseBody
    public DenunciaEstadoDTO getDenunciaEstado(@PathVariable(value = "id") Integer id) {
        return estadoService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DenunciaEstadoDTO save(@Valid @RequestBody DenunciaEstadoDTO denunciaEstadoDTO) {
        return estadoService.save(denunciaEstadoDTO);
    }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public DenunciaEstadoDTO updateEstado(@Valid @RequestBody DenunciaEstadoDTO estadoDTO, @PathVariable(value = "id") Integer id) {
        return estadoService.update(estadoDTO, id);
    }
}

