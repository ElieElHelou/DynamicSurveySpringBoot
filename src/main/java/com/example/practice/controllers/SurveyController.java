package com.example.practice.controllers;

import com.example.practice.domains.Survey;
import com.example.practice.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="surveyAPI/v1")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController (SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping(path = "/Survey/{id}")
    public Survey getSurveyById(@PathVariable("id")Long id){
        return surveyService.getSurveyById(id);
    }

    @PostMapping(path = "/newSurvey")
    public void createSurvey(@RequestBody Survey survey){
        surveyService.createSurvey(survey);
    }


    @GetMapping(path="/surveys")
    public List<Survey> getSurveyList() {
        return surveyService.getSurveyList();
    }

}
