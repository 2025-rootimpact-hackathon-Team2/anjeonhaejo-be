package com.rootimpact.anjeonhaejo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnjeonhaejoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnjeonhaejoApplication.class, args);
    }

}
