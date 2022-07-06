package com.example.practice.services;

import com.example.practice.domains.Survey;
import com.example.practice.domains.Token;
import com.example.practice.repositories.SurveyRepository;
import com.example.practice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
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
        boolean exists = surveyRepository.existsById(survey_id);
        if (!exists){
            throw new IllegalStateException("Survey does not exist!");
        }
        Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);
        Token token = new Token((surveyOptional.get()));
        tokenRepository.save(token);
    }

    public void sendToken(long survey_id, String to) {
        boolean exists = surveyRepository.existsById(survey_id);
        if (!exists){
            throw new IllegalStateException("Survey does not exist!");
        }

        Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);
        String title = surveyOptional.get().getTitle();


        long present = tokenRepository.existsBySurveyId(survey_id);
        if (present == 0){
            throw new IllegalStateException("No active token pointing to this survey exist!");
        }

        Optional <List<Token>> tokenOptional = tokenRepository.getTokenValueBySurveyId(survey_id);
        int min = 0;
        int max = tokenOptional.get().size() - 1;
        long token_id = tokenOptional.get().get((int)(Math.random()*(max-min+1)+min)).getId();

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

    public List<Token> getTokenList(long survey_id) {
        long present = tokenRepository.existsBySurveyId(survey_id);
        if (present == 0){
            throw new IllegalStateException("No active token pointing to this survey exist!");
        }

        Optional <List<Token>> tokenOptional = tokenRepository.getTokenValueBySurveyId(survey_id);
        return tokenOptional.get();
    }
}
