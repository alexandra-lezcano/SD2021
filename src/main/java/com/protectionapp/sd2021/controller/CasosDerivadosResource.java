package com.protectionapp.sd2021.controller;


import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.casosDerivados.CasosDerivadosServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/casosDerivados")
public class CasosDerivadosResource {


    @Autowired
    private CasosDerivadosServiceImpl cDService;


    @GetMapping("/{id}")
    public CasosDerivadosDTO getById(@PathVariable(value = "id") Integer cDId) {
        return cDService.getById(cDId);
    }


    @GetMapping(path = "page/{page_num}")
    public CasosDerivadosResult getCasosDerivados(@PathVariable(value = "page_num") Integer pageNum) {
        return cDService.getAll(PageRequest.of(pageNum, 5));
    }

    @GetMapping(path = "/")
    public CasosDerivadosResult getAll(Pageable page) {
        return cDService.getAll(page);
    }


    @PostMapping()
    public CasosDerivadosDTO save(@Valid @RequestBody CasosDerivadosDTO cDDto) {
        return cDService.save(cDDto);
    }

    @DeleteMapping("/{id}")
    public CasosDerivadosDTO delete(@PathVariable(value = "id") Integer id) {
        return cDService.delete(id);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CasosDerivadosDTO update(@RequestBody CasosDerivadosDTO casoDerivadosDTO, @PathVariable(value = "id") Integer id) {
        return cDService.update(casoDerivadosDTO, id);

    }






}

