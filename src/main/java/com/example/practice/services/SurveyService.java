package com.example.practice.services;

import com.example.practice.repositories.SurveyRepository;
import com.example.practice.domains.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    public SurveyService(){};

    public void createSurvey(Survey survey) {
        boolean exists = surveyRepository.existsById(survey.getId());
        if (exists)throw new IllegalStateException("Survey already exists");
        surveyRepository.save(survey);
    }

    public List<Survey> getSurveyList (){return surveyRepository.findAll();}

    public Survey getSurveyById(Long id) {
        boolean exists = surveyRepository.existsById(id);
        if (!exists) throw new IllegalStateException("Survey does not exist!");
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        return surveyOptional.get();
    }

}
