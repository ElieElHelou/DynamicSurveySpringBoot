package com.example.practice.configuration;

import com.example.practice.domains.Question;
import com.example.practice.repositories.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuestionTableConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    CommandLineRunner commandLineRunner2(QuestionRepository questionRepository){
        return args -> {
            Question q1 = new Question(
                    1L,"Dummy 1 with Id",1
            );

            Question q2 = new Question(
                    2L,
                    "Dummy 2 with Id", 0
            );

            Question q3 = new Question(
                    "Dummy without Id",
                    2
            );

            questionRepository.saveAll(
                    List.of(q1,q2,q3)
            );
        };
    }
}
