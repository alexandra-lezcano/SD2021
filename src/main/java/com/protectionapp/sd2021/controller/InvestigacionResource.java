package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.investigacion.InvestigacionDTO;
import com.protectionapp.sd2021.dto.investigacion.InvestigacionResult;
import com.protectionapp.sd2021.service.investigacion.InvestigacionServiceImpl;
import com.protectionapp.sd2021.utils.Configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/investigaciones")
public class InvestigacionResource {
    @Autowired
    Configurations configurations;

    @Autowired
    private InvestigacionServiceImpl investigacionService;

    @GetMapping("/{id}")
    public InvestigacionDTO getById(@PathVariable(value = "id")Integer id){return investigacionService.getById(id);}

    @GetMapping("page/{pageNum}")
    public InvestigacionResult getAll(@PathVariable(value = "pageNum") Integer pageNum){
        return investigacionService.getAll(PageRequest.of(pageNum, configurations.getItemsPaginacion()));
    }

    @GetMapping(path = "page/{page_num}/{size}")
    @ResponseBody
    public InvestigacionResult getAll(@PathVariable(value = "page_num") Integer pageNum, @PathVariable(value="size") Integer size) {
        return investigacionService.getAll(PageRequest.of(pageNum, size));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public InvestigacionDTO save(@Valid @RequestBody InvestigacionDTO dto) {
        return investigacionService.save(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvestigacionDTO update(@RequestBody InvestigacionDTO dto, @PathVariable(value = "id") Integer id) {
        return investigacionService.update(dto, id);

    }
    @DeleteMapping("/{id}")
    public InvestigacionDTO delete(@PathVariable(value = "id") Integer id) {
        return investigacionService.delete(id);
    }
}
