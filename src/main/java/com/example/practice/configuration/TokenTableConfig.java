package com.example.practice.configuration;

import com.example.practice.repositories.TokenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenTableConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    CommandLineRunner commandLineRunner4(TokenRepository tokenRepository){
        return args -> {};
    }

}