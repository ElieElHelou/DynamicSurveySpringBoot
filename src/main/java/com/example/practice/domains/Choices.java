package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Choices {
    @Id
    @SequenceGenerator(
            name = "choices_seq",
            sequenceName = "choices_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "choices_seq"
    )
    private long id;

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @JsonIgnore(value = true)
    @JsonBackReference
    private Question question;

    private String description;

    public Choices (){}

    public Choices (long id, String description){
        this.id = id;
        this.description = description;
    }
    public Choices (long id, String description, Question question){
        this.id = id;
        this.question = question;
        this.description = description;
    }

    public Choices (Question question, String description){
        this.question = question;
        this.description = description;
    }

//    @Override
//    public String toString(){
//        String display = "Choice " + this.id + " {\n" +
//                "Description='" + this.description + "\n" +
//                "Parent Question ID='" + /*this.question.getId()*/ + '\'' + "\n"
//                + "}";
//
//        return display;
//    }

    public long getId() {
        return this.id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}