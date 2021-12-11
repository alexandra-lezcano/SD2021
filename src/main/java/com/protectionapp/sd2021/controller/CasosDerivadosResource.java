package com.protectionapp.sd2021.controller;


import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosDTO;
import com.protectionapp.sd2021.dto.casosDerivados.CasosDerivadosResult;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoResult;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.casosDerivados.CasosDerivadosServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/casosDerivados")
public class CasosDerivadosResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private CasosDerivadosServiceImpl cDService;


    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public CasosDerivadosDTO getById(@PathVariable(value = "id") Integer cDId) {
        return cDService.getById(cDId);
    }


    @GetMapping(path = "page/{page_num}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public CasosDerivadosResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return cDService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page")
    public CasosDerivadosResult getAll() {
        return cDService.getAll(PageRequest.of(0, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @ResponseBody
    public CasosDerivadosResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value = "size") Integer size){
        return cDService.getAll(PageRequest.of(0, size));
    }

    @PostMapping()
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public CasosDerivadosDTO save(@Valid @RequestBody CasosDerivadosDTO cDDto) {
        return cDService.save(cDDto);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_TSOCIAL"})

    public CasosDerivadosDTO delete(@PathVariable(value = "id") Integer id) {
        return cDService.delete(id);
    }
    @PutMapping("/{id}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    @ResponseStatus(HttpStatus.OK)
    public CasosDerivadosDTO update(@RequestBody CasosDerivadosDTO casoDerivadosDTO, @PathVariable(value = "id") Integer id) {
        return cDService.update(casoDerivadosDTO, id);

    }
}

