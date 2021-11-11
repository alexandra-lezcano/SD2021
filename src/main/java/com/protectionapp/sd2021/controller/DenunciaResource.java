package com.protectionapp.sd2021.controller;


import com.protectionapp.sd2021.dao.denuncia.IDenunciaDao;
import com.protectionapp.sd2021.dto.denuncia.DenunciaDTO;
import com.protectionapp.sd2021.dto.denuncia.DenunciaResult;
import com.protectionapp.sd2021.service.denuncia.DenunciaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/denuncia")
public class DenunciaResource {
    @Autowired
    private DenunciaServiceImpl denunciaService;

    @GetMapping("/{id}")
    public DenunciaDTO getById(@PathVariable(value="id") Integer id){
        return denunciaService.getById(id);
    }

    /*Ver cantidad de resultados por pagina aca tambien*/
    @GetMapping(path = "page/{num}")
    public DenunciaResult getDenuncias(@PathVariable(value = "num") Integer num){
        return denunciaService.getAll(PageRequest.of(num,5));
    }

    /*Post en construccion*/
    @PostMapping(   path="denuncia",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public DenunciaDTO postDenuncia(@RequestBody DenunciaDTO denunciaDTO) {
        return denunciaService.save(denunciaDTO);
    }

   



}
