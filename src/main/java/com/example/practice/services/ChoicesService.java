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

    private final QuestionRepository questionRepository;
    private final ChoicesRepository choicesRepository;

    @Autowired
    public ChoicesService(QuestionRepository questionRepository,
                         ChoicesRepository choicesRepository){
        this.questionRepository = questionRepository;
        this.choicesRepository = choicesRepository;
    }

    public void defineChoices(Choices choice, long question_id){
        Optional <Question> questionOptional = questionRepository.findById(question_id);
        boolean exists = choicesRepository.existsById(choice.getId());
        if (questionOptional.isEmpty()){
            throw new IllegalStateException("Question does not exist!");
        }

        if (questionOptional.get().getMode() == 0){
            throw new IllegalStateException("Question with free answer selection mode cannot have predetermined answers!");
        }

        if (exists){
            throw new IllegalStateException("Choice already exists!");
        }

        Choices entity = new Choices(choice.getId(), choice.getDescription(),questionOptional.get());

        choicesRepository.save(entity);
    }

    public List<Choices> getAllByQuestionId(Long id) {
        Optional<List<Choices>> exists = choicesRepository.getChoicesByQuestionId(id);
        if (exists.isEmpty()) {
            throw new IllegalStateException("Question does not exist!");
        }
        return exists.get();
    }
}
