package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaEstadoDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaEstadoResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.denuncia.DenunciaEstadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public DenunciaEstadoDTO getById(@PathVariable(value = "id") Integer id) {
        return estadoService.getById(id);
    }

    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public DenunciaEstadoResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return estadoService.getAll(PageRequest.of(pageNum, 5));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )
    public DenunciaEstadoResult getllAllNotPaginated() {
        return estadoService.getAllNotPaginated();
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

    @DeleteMapping("/{id}")
    public DenunciaEstadoDTO delete(@PathVariable(value = "id") Integer id) {
        return estadoService.delete(id);
    }

}

