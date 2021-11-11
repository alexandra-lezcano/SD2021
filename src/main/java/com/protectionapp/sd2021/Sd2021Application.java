package com.protectionapp.sd2021;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/* Levantar la app usando el Tomcat que viene por defecto */
@SpringBootApplication
public class Sd2021Application {
    public static void main(String[] args) {
        SpringApplication.run(Sd2021Application.class, args);
    }

}
