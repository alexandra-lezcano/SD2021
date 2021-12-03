package com.protectionapp.sd2021;

import com.protectionapp.sd2021.dto.denuncia.*;
import com.protectionapp.sd2021.dto.localization.CityDTO;
import com.protectionapp.sd2021.dto.user.UserDTO;
import com.protectionapp.sd2021.dto.denuncia.SujetoDto;

import com.protectionapp.sd2021.service.denuncia.*;
import com.protectionapp.sd2021.service.location.ICityService;
import com.protectionapp.sd2021.service.user.IUserService;
import com.protectionapp.sd2021.utils.Configurations;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
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

    private static final int INDIRECT_CALL_TRANSACTION = 1;
    private static final int INDIRECT_CALL_NO_TRANSACTION = 2;
    private static final int DIRECT_CALL = 3;

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

    public static void testTransactionSupport (ApplicationContext applicationContext){
        int test = DIRECT_CALL;
        logger.info("[TEST] Transaction Propagation.SUPPORTS");

        ITipoSujetoService tipoSujetoService = applicationContext.getBean(ITipoSujetoService.class);
        ISujetoService sujetoService = applicationContext.getBean(ISujetoService.class);

        switch (test){
            case INDIRECT_CALL_TRANSACTION:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar desde otro metodo con transaccion y con fallo");
                } else {
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar desde otro metodo con transaccion y con exito");
                }
                sujetoService.deleteTipo();
                break;
            case INDIRECT_CALL_NO_TRANSACTION:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar desde otro metodo sin transaccion y con fallo");
                } else {
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar desde otro metodo sin transaccion y con exito");
                }
                sujetoService.deleteTipoNoTransaction();
                break;
            case DIRECT_CALL:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar directamente con fallo");
                }else {
                    logger.info("[TEST] Transaction Propagation.SUPPORTS: invocar directamente con exito");
                }
                logger.info("[TEST] Transaction Propagation.SUPPORTS: No se crean transacciones");
                TipoSujetoDTO nuevo = new TipoSujetoDTO();
                nuevo.setNombre("Prueba");
                TipoSujetoDTO guardado = tipoSujetoService.save(nuevo);
                logger.info("[TEST] Transaction Propagation.SUPPORTS: Se crea el tipoSujeto con id: "+ guardado.getId());
                TipoSujetoDTO borrado = tipoSujetoService.delete(guardado.getId());
                logger.info("[TEST] Transaction Propagation.SUPPORTS: Se borra el tipoSujeto con id: "+ borrado.getId());
                break;
            default:
                break;
        }
    }

    public static void testTransactionRequiresNew (ApplicationContext applicationContext){
        int test = INDIRECT_CALL_TRANSACTION;

        ISujetoService sujetoService = applicationContext.getBean(ISujetoService.class);
        ITipoSujetoService tipoSujetoService = applicationContext.getBean(ITipoSujetoService.class);

        logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: Creando objetos para la prueba");
        SujetoDto sujeto = new SujetoDto();
        sujeto.setNombre("Carlos");
        sujeto.setTipo_id(1);
        SujetoDto guardado = sujetoService.save(sujeto);
        Integer idGuardado = guardado.getId();
        logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: se creo el Sujeto con id" + idGuardado);
        logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: start test");
        switch (test){
            case INDIRECT_CALL_TRANSACTION:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar desde otro metodo con transaccion y con fallo");
                } else {
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar desde otro metodo con transaccion y con exito");
                }
                SujetoDto updated = tipoSujetoService.updateSujetoNombre(guardado, idGuardado, "Sebastian");
                logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: se actualizo el sujeto con id "+ updated.getId());
                break;
            case INDIRECT_CALL_NO_TRANSACTION:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar desde otro metodo sin transaccion y con fallo");
                } else {
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar desde otro metodo sin transaccion y con exito");
                }
                SujetoDto updatedNoTransaction = tipoSujetoService.updateSujetoNombreNoTransaction(guardado, idGuardado, "Roberto");
                logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: se actualizo el sujeto con id "+ updatedNoTransaction.getId());
                break;
            case DIRECT_CALL:
                if(sujetoService.getConf().isTransactionTest()){
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar directamente y con fallo");
                } else {
                    logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: invocar directamente y con exito");
                }
                guardado.setNombre("Diego");
                SujetoDto updatedDirect = sujetoService.update(guardado, idGuardado);
                logger.info("[TEST] Transaction Propagation.REQUIRES_NEW: se actualizo el sujeto con id "+ updatedDirect.getId());
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Sd2021Application.class, args);

        //testTransactionNever(applicationContext);
        testTransactionSupport(applicationContext);
        //testTransactionRequiresNew(applicationContext);
    }
}
