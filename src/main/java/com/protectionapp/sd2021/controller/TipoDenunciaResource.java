package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.denuncia.TipoDenunciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public TipoDenunciaResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return tipoDenunciaService.getAll(PageRequest.of(pageNum, 5));
    }

    @GetMapping()
    @ResponseBody
    public TipoDenunciaResult getAll() {
        return tipoDenunciaService.getAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TipoDenunciaDTO save(@Valid @RequestBody TipoDenunciaDTO dto) {
        return tipoDenunciaService.save(dto);
    }


}
