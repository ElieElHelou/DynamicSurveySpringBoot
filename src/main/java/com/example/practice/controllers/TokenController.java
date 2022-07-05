package com.example.practice.controllers;

import com.example.practice.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="surveyAPI/v1")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController (TokenService tokenService){this.tokenService = tokenService;}

    @PostMapping(path = "/Survey-{id}/sendToken")
    public void GenerateAndSend(long survey_id)
    {
        long token_id = tokenService.generate(survey_id);
        tokenService.send(token_id);
    }
}
