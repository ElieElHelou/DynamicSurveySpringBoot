package com.example.practice.controllers;

import com.example.practice.domains.Question;
import com.example.practice.domains.Survey;
import com.example.practice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="surveyAPI/v1")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController (QuestionService questionService){this.questionService = questionService;}

    @GetMapping(path = "/Question/{id}")
    public Question getQuestionById(@PathVariable("id")Long id){
        return questionService.getQuestionById(id);
    }

    @PostMapping(path = "/Survey-{id}/newQuestion")
    public void defineQuestion(@RequestBody Question question,
                               @PathVariable("id") Long survey_id){
        questionService.defineQuestion(question, survey_id);
    }

    @GetMapping(path="/Survey/{id}/questions")
    public Optional <List<Question>> getQuestionList(@PathVariable("id") Long id) {
        return questionService.getAllBySurveyId(id);
    }

}
