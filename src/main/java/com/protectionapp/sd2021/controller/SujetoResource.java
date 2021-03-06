package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;
import com.protectionapp.sd2021.dto.denuncia.SujetoResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.denuncia.SujetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public SujetoResult getAll(@PathVariable(value = "page_num") Integer pageNum) {
        return sujetoService.getAll(PageRequest.of(pageNum, 5));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )
    public SujetoResult getllAllNotPaginated() {
        return sujetoService.getllAllNotPaginated();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SujetoDto save(@Valid @RequestBody SujetoDto sujetoDto){
        return sujetoService.save(sujetoDto);
    }

    /*Put en construccion*/
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SujetoDto update(@Valid @RequestBody SujetoDto sujetoDto, @PathVariable(value = "id") Integer id) {
        return sujetoService.update(sujetoDto, id);
    }

    /*Delete en construccion*/
    @DeleteMapping("/{id}")
    public SujetoDto delete(@PathVariable(name = "id") Integer id) {
        return sujetoService.delete(id);
    }


}
