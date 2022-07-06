package com.example.practice.services;

import com.example.practice.domains.Choices;
import com.example.practice.domains.Question;
import com.example.practice.repositories.ChoicesRepository;
import com.example.practice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChoicesService {

    private final QuestionRepository questionRepository;
    private final ChoicesRepository choicesRepository;

    @Autowired
    public ChoicesService(QuestionRepository questionRepository,
                         ChoicesRepository choicesRepository){
        this.questionRepository = questionRepository;
        this.choicesRepository = choicesRepository;
    }

    public void defineChoices(Choices choice, long question_id){
        Optional<Question> questionOptional = questionRepository.findById(question_id);
        Optional<Choices> choiceOptional = choicesRepository.findChoiceById(choice.getId());
        if (questionOptional.isEmpty()){
            throw new IllegalStateException("Question does not exist!");
        }

        if (choiceOptional.isPresent()){
            throw new IllegalStateException("Choice already exists!");
        }

        Choices entity = new Choices(choice.getId(), choice.getDescription(),questionOptional.get());

        choicesRepository.save(entity);
    }
}
