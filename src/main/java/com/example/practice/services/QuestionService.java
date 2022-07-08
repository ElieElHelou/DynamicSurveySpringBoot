package com.example.practice.services;

import com.example.practice.domains.Question;
import com.example.practice.domains.Survey;
import com.example.practice.repositories.QuestionRepository;
import com.example.practice.repositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SurveyRepository surveyRepository;

    public QuestionService(){}

    //    free entry : 0
    //    single select : 1
    //    multiple select : 2

    public void defineQuestion(Question question) {
        if (question != null) {
            boolean exists = questionRepository.existsById(question.getId());
            Optional<Survey> surveyOptional = surveyRepository.findById(question.getSurvey_id());
            if (surveyOptional.isEmpty()) throw new IllegalStateException("Survey does not exist!");
            if (exists) throw new IllegalStateException("Question already exists!");
            Question entity = new Question(question.getId(), question.getDescription(), question.getMode(), surveyOptional.get());
            questionRepository.save(entity);
        }
        else throw new IllegalStateException("Data transfer error!");
    }

    public Question getQuestionById(Long id) {
        Optional<Question> exists = questionRepository.findById(id);
        if (exists.isEmpty()) throw new IllegalStateException("Question does not exist!");
        return exists.get();
    }

    public List<Question> getAllBySurveyId(Long id) {
        Optional<List<Question>> exists = questionRepository.findAllBySurveyId(id);
        if (exists.isEmpty()) throw new IllegalStateException("Question does not exist!");
        return exists.get();
    }
}
