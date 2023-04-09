package org.example.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "\"event_questions\"")
public class EEventQuestions implements Serializable {

    @Id
    @Column(name = "\"event_questions_id\"")
    private Integer event_questions_id;

    @Column(name = "\"event_id\"")
    private Integer event_id;

    @Column(name = "\"question\"")
    private String question;

    @Column(name = "\"answer\"")
    private String answer;

    public EEventQuestions(){}

    public EEventQuestions(Integer event_questions_id, Integer event_id, String question, String answer){
        this.event_questions_id = event_questions_id;
        this.event_id = event_id;
        this.question = question;
        this.answer = answer;
    }

    public Integer getEvent_questions_id() {
        return event_questions_id;
    }

    public void setEvent_questions_id(Integer event_questions_id) {
        this.event_questions_id = event_questions_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
