package com.example.practice.repositories;

import com.example.practice.domains.Choices;
import com.example.practice.domains.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.survey.id = ?1")
    Optional <List<Question>> findAllBySurveyId(Long id);
}
