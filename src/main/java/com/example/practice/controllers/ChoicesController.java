package com.example.practice.controllers;

import com.example.practice.domains.Choices;
import com.example.practice.services.ChoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="surveyAPI/v1")
public class ChoicesController {

    private final ChoicesService choicesService;

    @Autowired
    public ChoicesController (ChoicesService choicesService){this.choicesService = choicesService;}

    @PostMapping(path = "/Question-{id}/newChoice")
    public void defineAnswer(@RequestBody Choices choice,
                             @PathVariable("id") Long question_id)
    {
        choicesService.defineChoices(choice, question_id);
    }


}
