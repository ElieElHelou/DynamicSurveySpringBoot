package com.example.practice.controllers;

import com.example.practice.domains.Choices;
import com.example.practice.services.ChoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/choices")
public class ChoicesController {
    @Autowired
    private ChoicesService choicesService;
    public ChoicesController (){}
    @PostMapping(path = "")
    public void defineAnswer(@RequestBody Choices choice) {choicesService.defineChoices(choice);}
    @GetMapping(path = "/{id}")
    public Choices getChoiceById(@PathVariable("id")Long id){return choicesService.getChoiceById(id);}
    @GetMapping(path = "/question/{id}")
    public List<Choices> getChoicesList(@PathVariable("id")Long id) {return choicesService.getAllByQuestionId(id);}
}
