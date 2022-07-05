package com.example.practice.configuration;

import com.example.practice.domains.Choices;
import com.example.practice.repositories.ChoicesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChoicesTableConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    CommandLineRunner commandLineRunner3(ChoicesRepository choicesRepository){
        return args -> {
            Choices c1 = new Choices(1L,"I'm talking to you dummy 1");
            Choices c2 = new Choices(2L,"I'm talking to you dummy 2");
            choicesRepository.saveAll(
                    List.of(c1,c2)
            );
        };
    }
}
