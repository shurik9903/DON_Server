package org.example.data.entity;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "\"event\"")
public class EEvent implements Serializable {

    @Id
    @Column(name = "\"event_id\"")
    private Integer event_id;

    @Column(name = "\"teacher_id\"")
    private Integer teacher_id;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"startDate\"")
    private String startDate;

    @Column(name = "\"endDate\"")
    private String endDate;

    @Column(name = "\"type\"")
    private String type;

    public EEvent(){}

    public EEvent(Integer event_id, Integer teacher_id, String name, String startDate, String endDate, String type){
        this.event_id = event_id;
        this.teacher_id = teacher_id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
