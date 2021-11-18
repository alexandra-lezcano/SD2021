package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.denuncia.TipoSujetoDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoSujetoResult;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
import com.protectionapp.sd2021.service.denuncia.TipoSujetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tiposSujetos")
public class TipoSujetoResource {
    @Autowired
    private TipoSujetoServiceImpl tipoSujetoService;

    @GetMapping("/{id}")
    public TipoSujetoDTO getById(@PathVariable(value = "id") Integer id) {
        return tipoSujetoService.getById(id);
    }

    @GetMapping(path = "page/{num}")
    public TipoSujetoResult getTiposSujetos(@PathVariable(value = "num") Integer num){
        return tipoSujetoService.getAll(PageRequest.of(num, 5));
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
