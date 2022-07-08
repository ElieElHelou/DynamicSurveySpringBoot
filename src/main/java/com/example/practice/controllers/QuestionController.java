package com.example.practice.controllers;

import com.example.practice.domains.Question;
import com.example.practice.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="v1/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    public QuestionController (){}
    @GetMapping(path = "/{id}")
    public Question getQuestionById(@PathVariable("id")Long id){
        return questionService.getQuestionById(id);
    }
    @PostMapping(path = "")
    public void defineQuestion(@RequestBody Question question){questionService.defineQuestion(question);}
    @GetMapping(path="/survey/{id}")
    public List<Question> getQuestionList(@PathVariable("id") Long id) {
        return questionService.getAllBySurveyId(id);
    }

}
