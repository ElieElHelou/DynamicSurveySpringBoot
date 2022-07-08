package com.example.practice.controllers;

import com.example.practice.domains.Survey;
import com.example.practice.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="v1/surveys")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;
    public SurveyController (){}
    @GetMapping(path = "/{id}")
    public Survey getSurveyById(@PathVariable("id")Long id){return surveyService.getSurveyById(id);}
    @PostMapping(path = "/{id}")
    public void createSurvey(@RequestBody Survey survey){surveyService.createSurvey(survey);}
    @GetMapping(path="")
    public List<Survey> getSurveyList() {return surveyService.getSurveyList();}
}
