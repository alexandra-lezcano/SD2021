package com.protectionapp.sd2021;

import com.protectionapp.sd2021.domain.casosDerivados.DepEstadoDomain;
import com.protectionapp.sd2021.dto.casosDerivados.DepEstadoDTO;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.localization.NeighborhoodDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.service.casosDerivados.DepEstadoServiceImpl;
import com.protectionapp.sd2021.service.casosDerivados.IDepEstadoService;
import com.protectionapp.sd2021.service.location.ICityService;
import com.protectionapp.sd2021.service.location.INeighborhoodService;
import com.protectionapp.sd2021.service.location.NeighborhoodServiceImpl;
import com.protectionapp.sd2021.service.user.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.SpringVersion;

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
    private static final boolean isTestForRollback = false;
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
            // Para que llamada desde servicio sea exitosa:
            // isServiceCall = true;
            // isTestForRollback = false;
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

    public static void testTransactionRequired(ApplicationContext applicationContext) {



        logger.info("[TEST] Transaction Propagation.REQUIRED");
        IDepEstadoService depEstadoService = applicationContext.getBean(IDepEstadoService.class);


        int option=2;
        switch(option) {
            case 1:
                //directo required exitoso y fallido
                logger.info("Test transaccion directa required - getById");
                depEstadoService.getById(13);
                return;
            case 2:
                //indirecto exitoso y fallido con transaccion y sin transaccion
                logger.info("Test transaccion indirecta required exitoso y fallido- metodo public testIndDirectRequired - delete");
             //   depEstadoService.testIndDirectRequired(13); //con transaccion
                depEstadoService.testIndDirectRequiredNT(13);//sin transaccion
                return;



        }


    }

    public static void testTransactionNotSupported(ApplicationContext applicationContext) {

        logger.info("[TEST] Transaction Propagation.NOTSUPPORTED");
        INeighborhoodService neighborhoodService = applicationContext.getBean(INeighborhoodService.class);


        int option=2;

        switch(option) {
            case 1:
                //directo exitoso y fallido - metodo de lectura get
                logger.info("TEST: Directo Not Supported con getbyid");
                logger.info("TEST: Voy a intentar hacer un get");
                neighborhoodService.getById(17);
                return;

            case 2:
                //Indirecto exitoso y fallido con transaccion y sin transaccion -
                logger.info("TEST: indirecto Not Supported");
              //  neighborhoodService.update(neighborhoodService.getById(17),29); //con transaccion
                neighborhoodService.testIndDirectNotSupportedNT(17);// Sin transaccion

                return;

        }

    }


        public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Sd2021Application.class, args);

           //testTransactionNever(applicationContext);
          //testTransactionRequired(applicationContext);
            testTransactionNotSupported(applicationContext);
    }
}
