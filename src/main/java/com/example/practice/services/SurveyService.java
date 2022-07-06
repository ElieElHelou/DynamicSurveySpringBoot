package com.example.practice.services;

import com.example.practice.repositories.SurveyRepository;
import com.example.practice.domains.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }

    public void createSurvey(Survey survey) {
        Optional<Survey> surveyOptional = surveyRepository.findById(survey.getId());
        if (surveyOptional.isPresent()){
            throw new IllegalStateException("Survey already exists");
        }
        surveyRepository.save(survey);
    }

    public List<Survey> getSurveyList (){
      return surveyRepository.findAll();

    }

    public Survey getSurveyById(Long id) {
        Optional<Survey> surveyOptional = surveyRepository.findById(id);
        if (surveyOptional.isEmpty()){
            throw new IllegalStateException("Survey does not exist!");
        }
        return surveyOptional.get();
    }
}
