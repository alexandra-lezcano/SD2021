package com.protectionapp.sd2021;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.location.ICityService;
import com.protectionapp.sd2021.service.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

/*  Levantar la app usando el Tomcat que viene por defecto
 *  Habilitar cache
 *  Leer memcached.xml para conectarse al servidor de cache*/
@EnableCaching
@ImportResource("classpath:memcached.xml")
@SpringBootApplication
public class Sd2021Application {
    private static final Logger logger = LogManager.getLogger(Sd2021Application.class);
    /* Si quiero testear llamada desde un servicio */
    private static final boolean isServiceCall = true;
    /* Si quiero que una llamada desde servicio falle*/
    private static final boolean isTestForRollback = true;
    /* Adicionalmente dentro de protectionapp.properties tengo la opcion de elegir si
    *  quiero que un metodo falle o no */

    /* Crear un userDTO y cityDTO, testear servicio con Transaction - Propagation.NEVER
    *  Invocar metodo directamente y desde un servicio transaccional
    *  Servicio: cityService.update
    *  Test para CASOS DE  ESCRITURA */
    public static void testTransactionNever(ApplicationContext applicationContext){
        logger.info("[TEST] Transaction Propagation.NEVER");

        IUserService userService = applicationContext.getBean(IUserService.class);
        ICityService cityService = applicationContext.getBean(ICityService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test user");

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("test city");
        CityDTO cityDTOSaved = cityService.save(cityDTO); // guardar primero la ciudad para obtener su id

        /*Test case: llamada desde metodo
        * case 1- rollback
        * case 2- llamada exitosa */
        if(isServiceCall){
            if(isTestForRollback){ // - para este test, setear -> transactions.test=true
                logger.info("[TEST] Transaction Propagation.NEVER - invocar metodo desde un servicio transactional");
                userService.rollbackPropagationNever(userDTO, cityDTOSaved);
            }
            logger.info("[TEST] Transaction Propagation.NEVER - invocar metodo desde un servicio SIN transaccion");
            userService.methodCallPropagationNever(userDTO, cityDTOSaved);

            /*Test case: llamada directa
            * case 1- exitoso - para este test, setear -> transactions.test=false
            * case 2- fallido - para este test, setear -> transactions.test=true */
        } else{
            logger.info("[TEST] Transaction Propagation.NEVER - invocar directamente");
            cityDTOSaved.setName("updated city");
            cityService.update(cityDTOSaved, cityDTOSaved.getId());
            userDTO.setCityId(cityDTOSaved.getId());
            userService.save(userDTO);
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Sd2021Application.class, args);

        testTransactionNever(applicationContext);
    }
}
