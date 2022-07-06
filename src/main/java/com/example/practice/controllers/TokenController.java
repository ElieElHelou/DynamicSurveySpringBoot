package com.example.practice.controllers;

import com.example.practice.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="surveyAPI/v1")
public class TokenController {

    private final TokenService tokenService;

    @Autowired
    public TokenController (TokenService tokenService){this.tokenService = tokenService;}

    @PostMapping(path = "/Survey/{id}/generatetoken")
    public void generateToken(@PathVariable("id") long survey_id)
    {
        tokenService.generateToken(survey_id);
    }

    @PostMapping(path = "/Survey/{id}/share")
    public void sendToken(@PathVariable("id") long survey_id,@RequestBody String to)
    {
        tokenService.sendToken(survey_id, to);
    }

}
