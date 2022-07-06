package com.example.practice.repositories;

import com.example.practice.domains.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query(value = "SELECT t FROM Token t WHERE ((t.survey.id = ?1) AND (t.consumed = false))")
    Optional <List<Token>> getTokenValueBySurveyId(long survey_id);

    @Query(value = "SELECT COUNT (*) FROM Token t WHERE ((t.survey.id = ?1) AND (t.consumed = false))")
    long existsBySurveyId(long survey_id);
}
