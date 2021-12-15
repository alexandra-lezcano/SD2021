package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaEstadoDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaEstadoResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.denuncia.DenunciaEstadoServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/denunciaEstados")
public class DenunciaEstadoResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private DenunciaEstadoServiceImpl estadoService;

    @GetMapping("/{id}")
    @ResponseBody
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public DenunciaEstadoDTO getById(@PathVariable(value = "id") Integer id) {
        return estadoService.getById(id);
    }

    @GetMapping(path = "page")
    @ResponseBody
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DenunciaEstadoResult getAll() {
        return estadoService.getAll(PageRequest.of(0,configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public DenunciaEstadoResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return estadoService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @ResponseBody
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DenunciaEstadoResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value = "size") Integer size) {
        return estadoService.getAll(PageRequest.of(pageNum, size));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public DenunciaEstadoResult getllAllNotPaginated() {
        return estadoService.getAllNotPaginated();
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public DenunciaEstadoDTO save(@Valid @RequestBody DenunciaEstadoDTO denunciaEstadoDTO) {
        return estadoService.save(denunciaEstadoDTO);
    }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})

    public DenunciaEstadoDTO updateEstado(@Valid @RequestBody DenunciaEstadoDTO estadoDTO, @PathVariable(value = "id") Integer id) {
        return estadoService.update(estadoDTO, id);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})

    public DenunciaEstadoDTO delete(@PathVariable(value = "id") Integer id) {
        return estadoService.delete(id);
    }

}

