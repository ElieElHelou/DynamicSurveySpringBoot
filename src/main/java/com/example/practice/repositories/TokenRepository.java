package com.example.practice.repositories;

import com.example.practice.domains.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT t FROM Token WHERE t.consumed = false AND t.survey.id = ?1")
    Optional<Token> findTokenBySurveyId(long survey_id);

    @Query("SELECT t FROM Token t WHERE t.id = ?1")
    Optional<Token> findTokenById(long id);

}
