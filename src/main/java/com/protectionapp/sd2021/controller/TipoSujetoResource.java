package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.*;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
import com.protectionapp.sd2021.service.denuncia.TipoSujetoServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tiposSujetos")
public class TipoSujetoResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private TipoSujetoServiceImpl tipoSujetoService;

    @GetMapping("/{id}")

    public TipoSujetoDTO getById(@PathVariable(value = "id") Integer id) {
        return tipoSujetoService.getById(id);
    }

    @GetMapping(path = "page")
    @ResponseBody

    public TipoSujetoResult getAll() {
        return tipoSujetoService.getAll(PageRequest.of(0, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}")
    @ResponseBody

    public TipoSujetoResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return tipoSujetoService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @ResponseBody

    public TipoSujetoResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value = "size") Integer size) {
        return tipoSujetoService.getAll(PageRequest.of(pageNum, size));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )

    public TipoSujetoResult getllAllNotPaginated() {
        return tipoSujetoService.getllAllNotPaginated();
    }

    /*Post en construccion*/
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody

    public TipoSujetoDTO postTipoSujeto(@Valid @RequestBody TipoSujetoDTO tipoSujetoDTO) {
        return tipoSujetoService.save(tipoSujetoDTO);
    }

    /*Put en construccion*/
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)

    public TipoSujetoDTO updateTipoSujeto(@Valid @RequestBody TipoSujetoDTO tipoSujetoDTO, @PathVariable(value = "id") Integer id) {
        return tipoSujetoService.update(tipoSujetoDTO, id);
    }

    /*Delete en construccion*/
    @DeleteMapping("/{id}")

    public TipoSujetoDTO deleteTipoSujeto(@PathVariable(name = "id") Integer id) {
        return tipoSujetoService.delete(id);
    }


}
