package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Survey survey;

    @Column(name = "survey_id")
    private Long survey_id;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<Choices> choices = new ArrayList<>();

    public Question(){}

    public Question (long id, String description, int answer_type){
        this.id = id;
        this.description = description;
        this.answer_type = answer_type;
    }

    public Question (String description, int answer_type){
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

    public Long getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(Long survey_id) {
        this.survey_id = survey_id;
    }
}
