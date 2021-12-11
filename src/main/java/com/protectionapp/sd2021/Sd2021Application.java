package com.protectionapp.sd2021;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/*  Levantar la app usando el Tomcat que viene por defecto
 *  Habilitar cache
 *  Leer memcached.xml para conectarse al servidor de cache*/
@EnableCaching
@EnableWebSecurity
@ImportResource({"classpath:memcached.xml","classpath:security-context.xml"})
@SpringBootApplication
public class Sd2021Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
            SpringApplication.run(Sd2021Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Sd2021Application.class);
    }
}
