package com.protectionapp.sd2021.controller;

import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.user.UserResult;
import com.protectionapp.sd2021.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid; // framework de validacion de Java Beans
/* @RestController:
 * El controlador es la definicion de nuestra API. El codigo de todas las demas capas sera
 * en vano si el controlador no esta bien construido. Recordar: servidores son procesos que
 * reciben un pedido, lo procesan y realizan acciones para retornar un recurso o brindar un
 * servicio, por eso los controladores son llamados Resource. Otro mas: los URL son la forma
 * en la que accedemos a los recusos del servidor.
 *
 * @RequestMapping: enruta los urls a esta clase */

@RestController
@RequestMapping("/users")

public class UserResource {

    /* IOC: inyeccion de control para no crear userService */
    @Autowired
    private UserServiceImpl userService;

    /* From alex to spring devs: Very cool spring boot!
     *  Requests que sean GET /users/5 van a llamar a este metodo
     *  @PathVariable mastica el URL y gracias a eso obtenemos que id se pidio */
    @GetMapping("/{id}")
    @ResponseBody
    public UserDTO getById(@PathVariable(value = "id") Integer userId) {
        return userService.getById(userId);
    }

    /* Ayuda a paginacion, ver Pageable
     * todo:
     *  1- consumir la cantidad de resultados por pagina desde config.properties
     *  2- tolerancia a fallos */
    @GetMapping(path = "page/{page_num}")
    @ResponseBody
    public UserResult getUsers(@PathVariable(value = "page_num") Integer pageNum) {
        return userService.getAll(PageRequest.of(pageNum, 5));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDTO save(@Valid @RequestBody UserDTO userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO updateUser(@RequestBody UserDTO newUserDTO, @PathVariable(value = "id") Integer userId) {
        return userService.update(newUserDTO, userId);

    }
}
