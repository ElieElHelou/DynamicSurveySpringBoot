package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_seq",
            sequenceName = "question_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_seq"
    )
    private long id;

    private String description;

    private int answer_type;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore(value = true)
    @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    @OneToMany(mappedBy = "question")
    private List<Choices> choices = new ArrayList<>();

    public Question(){}

    public Question (long id, String description, int answer_type){
        this.id = id;
        this.description = description;
        this.answer_type = answer_type;
    }

    public Question (String description, int answer_type){
        this.id = id;
        this.description = description;
        this.answer_type = answer_type;
    }

    public Question (long id, String description, int answer_type, Survey survey){
        this.id = id;
        this.description = description;
        this.answer_type = answer_type;
        this.survey = survey;
    }

    public Question(String description, int answer_type, Survey survey){
        this.description = description;
        this.answer_type = answer_type;
        this.survey = survey;
    }

    public long getId() {
        return this.id;
    }

//    @Override
//    public String toString(){
//        String info = "Question "+ this.id + " {\n" +
//                "Description='" + this.description + '\'' + "\n" +
//                "Answer Type=" + this.answer_type + "\n" +
//                "Parent Survey ID='" + /*this.survey.getId()*/ + '\'' + "\n" +
//                "Choices={";
//
//        for (Choices c : choices){
//            info = info + "\n" + c.toString();
//        }
//
//        info = info + "}";
//
//        return info;
//    }

    public void setMode(int mode) {
        this.answer_type = mode;
    }

    public int getMode() {
        return answer_type;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Collection<Choices> getChoices() {
        return choices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
