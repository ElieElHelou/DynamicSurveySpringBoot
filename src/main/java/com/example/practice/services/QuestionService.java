package com.example.practice.services;

import com.example.practice.domains.Question;
import com.example.practice.domains.Survey;
import com.example.practice.repositories.QuestionRepository;
import com.example.practice.repositories.SurveyRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    private final SurveyRepository surveyRepository;

    @Autowired
    public QuestionService(SurveyRepository surveyRepository,
                         QuestionRepository questionRepository){
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
    }

    //    free entry : 0
    //    single select : 1
    //    multiple select : 2

    public void defineQuestion(Question question, long survey_id) {
        Optional<Question> questionOptional = questionRepository.findById(question.getId());
        Optional<Survey> surveyOptional = surveyRepository.findById(survey_id);

        if (!surveyOptional.isPresent()){
            throw new IllegalStateException("Survey does not exist!");
        }

        if (questionOptional.isPresent()){
            throw new IllegalStateException("Question already exists!");
        }

        Question entity = new Question(question.getId(), question.getDescription(), question.getMode(),surveyOptional.get());

        questionRepository.save(entity);
    }

    public Question getQuestionById(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (!questionOptional.isPresent()){
            throw new IllegalStateException("Question does not exist!");
        }
        return questionOptional.get();
    }

    public Optional <List<Question>> getAllBySurveyId(Long id) {
            return questionRepository.findBySurveyId(id);
    }
}
