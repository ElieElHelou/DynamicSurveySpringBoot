package com.example.practice.controllers;

import com.example.practice.domains.dto.SurveyTargetListDto;
import com.example.practice.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="v1/tokens")
public class TokenController {
    @Autowired
    private TokenService tokenService;
    public TokenController (){}
    @PostMapping(path = "/survey/{id}/generatetokens")
    public void generateToken(@PathVariable("id") long survey_id) {tokenService.generateTokens(survey_id);}
    @PostMapping(path = "/survey/{id}/share")
    public void sendToken(@PathVariable("id") long survey_id,@RequestBody SurveyTargetListDto surveyTargetList) {tokenService.sendToken(survey_id, surveyTargetList);}

}
