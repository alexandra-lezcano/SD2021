package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.service.denuncia.TipoDenunciaServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping("/tipoDenuncias")
public class TipoDenunciaResource {
    @Autowired
    Configurations configurations;

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private TipoDenunciaServiceImpl tipoDenunciaService;

    @GetMapping("/{id}")
    @ResponseBody
    public TipoDenunciaDTO getById(@PathVariable(value = "id") Integer id) {
        return tipoDenunciaService.getById(id);
    }

    /*
    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public TipoDenunciaResult getAllx(@PathVariable(value = "page_num") Integer pageNum) {
        logger.info("TEST alex - get page " + pageNum);
        return tipoDenunciaService.getAll(PageRequest.of(pageNum, 5));
    }*/

    @GetMapping(path = "/page")
    @ResponseBody
    public TipoDenunciaResult getAll() {
        return tipoDenunciaService.getAll(PageRequest.of(0,configurations.getItemsPaginacion()));
    }

    @GetMapping(path="/page/{page_num}")
    @ResponseBody
    public TipoDenunciaResult getAll(@PathVariable(value = "page_num") Integer page){
        return tipoDenunciaService.getAll(PageRequest.of(page,configurations.getItemsPaginacion()));
    }

    @GetMapping(path="/page/{page_num}/{size}")
    @ResponseBody
    public TipoDenunciaResult getAll(@PathVariable(value = "page_num") Integer page, @PathVariable(value="size") Integer size){
        return tipoDenunciaService.getAll(PageRequest.of(page,size));
    }

   @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )
    public TipoDenunciaResult getllAllNotPaginated() {
        return tipoDenunciaService.getllAllNotPaginated();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public TipoDenunciaDTO save(@Valid @RequestBody TipoDenunciaDTO dto) {
        return tipoDenunciaService.save(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TipoDenunciaDTO update(@RequestBody TipoDenunciaDTO dto, @PathVariable(value = "id") Integer id) {
        return tipoDenunciaService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    public TipoDenunciaDTO delete(@PathVariable(value = "id") Integer id) {
        return tipoDenunciaService.delete(id);
    }
}
