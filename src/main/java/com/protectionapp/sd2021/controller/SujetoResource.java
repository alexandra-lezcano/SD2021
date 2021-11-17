package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;
import com.protectionapp.sd2021.service.denuncia.SujetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sujetos")
public class SujetoResource {
    @Autowired
    private SujetoServiceImpl sujetoService;

    @GetMapping("/{id}")
    @ResponseBody
    public SujetoDto getById(@PathVariable(value="id") Integer id){
        return sujetoService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SujetoDto postSujeto(@Valid @RequestBody SujetoDto sujetoDto){
        return sujetoService.save(sujetoDto);
    }

    /*Put en construccion*/
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SujetoDto updateSujeto(@Valid @RequestBody SujetoDto sujetoDto, @PathVariable(value = "id") Integer id) {
        return sujetoService.update(sujetoDto, id);
    }

    /*Delete en construccion*/
    @DeleteMapping("/{id}")
    public SujetoDto deleteSujeto(@PathVariable(name = "id") Integer id) {
        return sujetoService.delete(id);
    }


}
