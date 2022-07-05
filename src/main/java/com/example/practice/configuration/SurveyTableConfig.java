package com.example.practice.configuration;


import com.example.practice.domains.Survey;
import com.example.practice.repositories.SurveyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SurveyTableConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    CommandLineRunner commandLineRunner1(SurveyRepository surveyRepository){
        return args -> {
            Survey survey1 = new Survey(
                    1L,
                    "TestSurvey1",
                    "Whatever comes to mind when describing a survey."/*,
                    new ArrayList<>(
                            Arrays.asList(
                                    new Question("What does thou seek?",0),
                                    new Question("Where do you hide your toothbrushes?",2)))*/);

            Survey survey2 = new Survey(
                    "TestSurvey2",
                    "Still have no idea what to write in here.");

            surveyRepository.saveAll(
                    List.of(survey1,survey2)
            );

        };
    }
}