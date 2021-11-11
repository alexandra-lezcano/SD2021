package com.protectionapp.sd2021.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.logging.ErrorManager;

@Controller
public class MiErrorController implements ErrorController
{
    private static final Logger logger = LogManager.getLogger(ErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request){

        //To do: ver mensajes de error
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()){
                logger.trace(ErrorManager.OPEN_FAILURE);
                return "error-404";
            }
            if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                logger.trace(ErrorManager.OPEN_FAILURE);
                return "error-500";
            }
        }
        logger.trace(ErrorManager.GENERIC_FAILURE);
        return "error";
    }

}
