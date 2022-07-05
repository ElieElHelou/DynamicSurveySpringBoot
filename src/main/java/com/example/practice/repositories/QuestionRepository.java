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

    @Query("SELECT q FROM Question q WHERE q.description = ?1")
    Optional <Question> findQuestionByDesc(String description);

    @Query("SELECT c FROM Choices c WHERE c.question.getId() = ?1")
    Optional<List<Choices>> findChoicesByQuestionId(long id);

    @Query("SELECT q FROM Question q WHERE q.survey_id.getId() = ?1")
    Optional <List<Question>> findBySurveyId(Long id);
}
