package com.example.practice.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
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
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "survey_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Survey survey;

    @Column(name = "survey_id")
    private long surveyId;

    public Token(){}

    public Token(long id, long surveyId){
        this.id = id;
        this.surveyId = surveyId;
        this.creation_date = Timestamp.valueOf(LocalDateTime.now());
    }

    public Token(long surveyId){
        this.surveyId = surveyId;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PrePersist
    public void prePersist(){
        if(uuid == null || (uuid != null && uuid.trim().equals(""))) uuid = UUID.randomUUID().toString();
    }


}
