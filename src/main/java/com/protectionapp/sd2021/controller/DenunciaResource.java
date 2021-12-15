package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.dto.denuncia.TipoDenunciaResult;
import com.protectionapp.sd2021.exception.DenunciaNotFoundException;
import com.protectionapp.sd2021.service.denuncia.DenunciaServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/denuncias")
public class DenunciaResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private DenunciaServiceImpl denunciaService;

    @GetMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public DenunciaDTO getById(@PathVariable(value = "id") Integer id) throws DenunciaNotFoundException {
        return denunciaService.getById(id);
    }

    @GetMapping(path = "page")
    public DenunciaResult getAll() throws DenunciaNotFoundException {
        return denunciaService.getAll(PageRequest.of(0, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{num}")
    @Secured({"ROLE_ADMIN"})
    public DenunciaResult getAll(@PathVariable(value = "num") Integer num) throws DenunciaNotFoundException {
        return denunciaService.getAll(PageRequest.of(num, 5));
    }

    @GetMapping(path = "page/{num}/{size}")
    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DenunciaResult getAll(@PathVariable(value = "num") Integer num, @PathVariable(value = "size") Integer size) throws DenunciaNotFoundException {
        return denunciaService.getAll(PageRequest.of(num, size));
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = "application/JSON"
    )

    @Secured({"ROLE_ADMIN","ROLE_TSOCIAL"})
    public DenunciaResult getllAllNotPaginated() {
        return denunciaService.getllAllNotPaginated();
    }


    /*Post en construccion*/
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Secured({"ROLE_ADMIN"})
    public DenunciaDTO postDenuncia(@Valid @RequestBody DenunciaDTO denunciaDTO) {
        return denunciaService.save(denunciaDTO);
    }

    /*Put en construccion*/
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public DenunciaDTO updateDenuncia(@Valid @RequestBody DenunciaDTO denunciaDTO, @PathVariable(value = "id") Integer id) {
        return denunciaService.update(denunciaDTO, id);
    }

    /*Delete en construccion*/
    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public DenunciaDTO deleteDenuncia(@PathVariable(name = "id") Integer id) {
        return denunciaService.delete(id);
    }


}
