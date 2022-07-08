package com.example.practice.services;

import com.example.practice.domains.Choices;
import com.example.practice.domains.Question;
import com.example.practice.repositories.ChoicesRepository;
import com.example.practice.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChoicesService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoicesRepository choicesRepository;

    public ChoicesService(){}

    public void defineChoices(Choices choice){
        if (choice != null){
            Optional <Question> questionOptional = questionRepository.findById(choice.getQuestion_id());
            boolean exists = choicesRepository.existsById(choice.getId());
            if (questionOptional.isEmpty())throw new IllegalStateException("Question does not exist!");
            if (questionOptional.get().getMode() == 0)throw new IllegalStateException("Question with free answer selection mode cannot have predetermined answers!");
            if (exists)throw new IllegalStateException("Choice already exists!");
            Choices entity = new Choices(choice.getId(), choice.getDescription(),questionOptional.get());
            choicesRepository.save(entity);
        }
        else throw new RuntimeException("Data transfer error!");
    }
    public List<Choices> getAllByQuestionId(Long id) {
        if (id == null) throw new RuntimeException("Error! Received null Id!");
        Optional<List<Choices>> exists = choicesRepository.getChoicesByQuestionId(id);
        if (exists.isEmpty()) throw new IllegalStateException("Question does not exist!");
        return exists.get();
    }
    public Choices getChoiceById(Long id) {
        if (id == null) throw new RuntimeException("Error! Received null Id!");
        Optional<Choices> exists = choicesRepository.findById(id);
        if (exists.isEmpty()) throw new IllegalStateException("Question does not exist!");
        return exists.get();
    }
}
