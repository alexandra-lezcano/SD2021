package com.protectionapp.sd2021.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:protectionapp.properties")
public class Configurations {
    public static final String CACHE_NOMBRE = "api-cache";

    @Value("${items.por.pagina}")
    private Integer ITEMS_PAGINA;

    @Value("${transactions.test}")
    private boolean transactionTest;
    public Integer getItemsPaginacion() {
        return ITEMS_PAGINA;
    }

    public boolean isTransactionTest() {
        return transactionTest;
    }
}
