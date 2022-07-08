package com.example.practice.services;

import com.example.practice.domains.Survey;
import com.example.practice.domains.Token;
import com.example.practice.domains.dto.SurveyTargetListDto;
import com.example.practice.repositories.SurveyRepository;
import com.example.practice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TokenService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private EmailingService emailingService;

    public List<String> generateTokens(long survey_id) {
        return generateTokens(survey_id, true, 1);
    }

    public List<String> generateTokens(long survey_id, boolean withCheck, int number) {
        if(withCheck) {
            boolean exists = surveyRepository.existsById(survey_id);
            if (!exists) throw new IllegalStateException("Survey does not exist!");
        }
        List<Token> tokens = new ArrayList<Token>();
        for(int i=0; i < number; i++) tokens.add(new Token(survey_id));
        tokens = tokenRepository.saveAll(tokens);
        List<String> response = new ArrayList<String>();
        for(int i=0; i < tokens.size(); i++) response.add(tokens.get(i).getUuid());
        return response;
    }

    public void sendToken(Long survey_id, SurveyTargetListDto surveyTargetList) {
        if(survey_id == null) throw new RuntimeException("Error! Received null Id");
        if(surveyTargetList != null && surveyTargetList.hasElements()){
            boolean exists = surveyRepository.existsById(survey_id);
            if (!exists) throw new IllegalStateException("Survey does not exist!");

            Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);
            String title = surveyOptional.get().getTitle();

            Optional <List<Token>> tokenOptional = tokenRepository.getTokenValueBySurveyId(survey_id);
            if(tokenOptional.isPresent() && tokenOptional.get() != null && tokenOptional.get().size() > 0){
                Map<String,String> clientMap = new HashMap<String,String>();
                List<String> tokens = generateTokens(survey_id, false, surveyTargetList.getTargetClients().size());
                if(tokens != null && tokens.size() > 0 && surveyTargetList.getTargetClients().size() == tokens.size()){
                    for(int i=0; i < surveyTargetList.getTargetClients().size(); i++)
                        clientMap.put(surveyTargetList.getTargetClients().get(i), tokens.get(i));
                    String error = emailingService.sendEmail(clientMap, title);
                    if(error != null) throw new RuntimeException(error);
                }
            } else {
                throw new IllegalStateException("AAA");
            }
        } else {
            throw new IllegalStateException("No active token pointing to this survey exist!");
        }
    }
}
