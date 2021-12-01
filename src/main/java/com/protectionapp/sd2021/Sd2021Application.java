package com.protectionapp.sd2021;

import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.location.ICityService;
import com.protectionapp.sd2021.service.user.IUserService;
import com.protectionapp.sd2021.utils.Configurations;
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
    private static final Configurations config = new Configurations();

    /* Crear un userDTO y cityDTO, testear servicio con Transaction - Propagation.NEVER
    *  Invocar metodo directamente y desde un servicio transaccional
    *  Servicio: cityService.update */
    public static void testTransactionNever(ApplicationContext applicationContext){
        logger.info("[TEST] Transaction Propagation.NEVER");

        IUserService userService = applicationContext.getBean(IUserService.class);
        ICityService cityService = applicationContext.getBean(ICityService.class);
        UserDTO userDTO = new UserDTO();
        userDTO.setName("test user");

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("test city");
        CityDTO cityDTOSaved = cityService.save(cityDTO); // guardar primero la ciudad para obtener su id

        if(config.isTransactionTest()){
            logger.info("[TEST] Transaction Propagation.NEVER - invocar metodo desde un servicio transactional");
            userService.rollbackPropagationNever(userDTO, cityDTOSaved);

        } else{
            logger.info("[TEST] Transaction Propagation.NEVER - invocar metodo directamente");

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
