package com.sandun.jobloke.config;

import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieSeriealizer {

    @Bean
    public CookieSameSiteSupplier sameSiteSupplier(){
        return CookieSameSiteSupplier.ofNone();
    }
}
