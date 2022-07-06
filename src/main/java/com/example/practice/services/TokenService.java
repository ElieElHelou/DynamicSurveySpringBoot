package com.example.practice.services;

import com.example.practice.domains.Survey;
import com.example.practice.domains.Token;
import com.example.practice.repositories.SurveyRepository;
import com.example.practice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final JavaMailSender emailSender;
    private final SurveyRepository surveyRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository, SurveyRepository surveyRepository, JavaMailSender emailSender){
        this.tokenRepository = tokenRepository;
        this.surveyRepository = surveyRepository;
        this.emailSender = emailSender;
    }

    public void generateToken(long survey_id) {
        Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);
        if (surveyOptional.isEmpty()){
            throw new IllegalStateException("Survey does not exist!");
        }

        Token token = new Token((surveyOptional.get()));
        tokenRepository.save(token);
    }

    public void sendToken(long survey_id,
                String to) {
        Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);
        if (surveyOptional.isEmpty()){
            throw new IllegalStateException("Survey does not exist!");
        }

        String title = surveyOptional.get().getTitle();

        Optional<Token> tokenOptional = tokenRepository.findTokenBySurveyId(survey_id);
        if (tokenOptional.isEmpty()){
            throw new IllegalStateException("No token pointing to this survey exist!");
        }

        long token_id = tokenOptional.get().getId();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("elieelhelou00@gmail.com");
        message.setTo(to);
        message.setSubject(title + "survey link");
        message.setText("You have been invited to participate in the survey titled'" +
                        title + "'. Use the following link to fill in and submit you answers: '" +
                        "localhost:8080/surveyAPI/v1/usetoken/" + token_id + "'."
        );
        emailSender.send(message);
    }
}
