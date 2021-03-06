package com.example.practice.repositories;

import com.example.practice.domains.Choices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoicesRepository extends JpaRepository<Choices, Long> {
    @Query("SELECT c FROM Choices c WHERE c.question.id = ?1")
    Optional<List<Choices>> getChoicesByQuestionId(long id);
}