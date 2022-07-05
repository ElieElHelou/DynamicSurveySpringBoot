package com.example.practice.services;

import com.example.practice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository){
        this.tokenRepository = tokenRepository;
    }

    public long generate(long survey_id) {
        return 0;
    }

    public void send(long token_id) {
    }
}
