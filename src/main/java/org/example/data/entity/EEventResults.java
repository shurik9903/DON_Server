package org.example.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "\"event_results\"")
public class EEventResults implements Serializable {

    @Id
    @Column(name = "\"event_results_id\"")
    private Integer event_results_id;

    @Column(name = "\"student_id\"")
    private Integer student_id;

    @Column(name = "\"event_id\"")
    private Integer event_id;

    @Column(name = "\"additional_materials_id\"")
    private Integer additional_materials_id;

    @Column(name = "\"grade\"")
    private Integer grade;

    public EEventResults(){}

    public EEventResults(Integer event_results_id, Integer student_id, Integer event_id, Integer additional_materials_id, Integer grade){
        this.event_results_id = event_results_id;
        this.student_id = student_id;
        this.event_id = event_id;
        this.additional_materials_id = additional_materials_id;
        this.grade = grade;
    }

    public Integer getEvent_results_id() {
        return event_results_id;
    }

    public void setEvent_results_id(Integer event_results_id) {
        this.event_results_id = event_results_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getAdditional_materials_id() {
        return additional_materials_id;
    }

    public void setAdditional_materials_id(Integer additional_materials_id) {
        this.additional_materials_id = additional_materials_id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
