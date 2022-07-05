package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
public class Token {
    @Id
    @SequenceGenerator(
            name = "token_seq",
            sequenceName = "token_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_seq"
    )
    private long id;

    private Timestamp creation_date;
    private boolean consumed = false;
    private Timestamp consumed_date;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore(value = true)
    @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "id")
    private Survey survey;

    public Token(){}

    public Token(long id, Survey survey){
        this.id = id;
        this.survey = survey;
        this.creation_date = Timestamp.valueOf(LocalDateTime.now());
    }

    public Token(Survey survey){
        this.survey = survey;
        this.creation_date = Timestamp.valueOf(LocalDateTime.now());
    }

    public void setConsumed_date(Timestamp consumed_date) {
        this.consumed_date = consumed_date;
    }

    public Timestamp getConsumed_date() {
        return consumed_date;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public boolean isConsumed() {
        return consumed;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }
}
