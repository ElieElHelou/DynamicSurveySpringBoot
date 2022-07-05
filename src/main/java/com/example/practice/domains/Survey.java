package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Survey {
    @Id
    @SequenceGenerator(
            name = "survey_seq",
            sequenceName = "survey_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "survey_seq"
    )
    private long id;

    private String title;

    private String description;

    private Timestamp creation_date;

    private boolean active = true;

//    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
//    @JsonBackReference
//    @JsonIgnore(value = true)
//    private List<Question> questions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    @JsonIgnore
    private List<Token> tokens;

    public Survey(){}

    public Survey(long id,
                  String title,
                  String description){
        this.id = id;
        this.title = title;
        this.description = description;
        this.creation_date = Timestamp.valueOf(LocalDateTime.now());
    }

    public Survey (String title,
                  String description){
        this.title = title;
        this.description = description;
        this.creation_date = Timestamp.valueOf(LocalDateTime.now());
    }


    public String getTitle() {
        return title;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public String info() {
//        String infosheet = this.toString();
//        infosheet = infosheet + "\nQuestions={";
//        for (Question q : this.questions){
//            infosheet = infosheet + "\n" + questions.toString();
//        }
//
//        infosheet = infosheet + "}\n}";
//
//        return infosheet;
//    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

//    @Override
//    public String toString(){
//        return "Survey " + this.id+ " {\n" +
//                "Title='" + this.title + '\'' +
//                "Description='" + this.description + "'" + "\n" +
//                "Creation Date=" + this.creation_date +
//                "Status=" + this.active +
//                "\n";
//    }

    public boolean isActive() {
        return active;
    }

//    public List<Question> getQuestions() {
//        return questions;
//    }

    public List<Token> getTokens() {
        return tokens;
    }
}
