package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.service.denuncia.TipoDenunciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tipoDenuncias")
public class TipoDenunciaResource {

    @Autowired
    private TipoDenunciaServiceImpl tipoDenunciaService;

    @GetMapping("/{id}")
    @ResponseBody
    public TipoDenunciaDTO getById(@PathVariable(value = "id") Integer id) {
        return tipoDenunciaService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TipoDenunciaDTO save(@Valid @RequestBody TipoDenunciaDTO dto) {
        return tipoDenunciaService.save(dto);
    }
}
