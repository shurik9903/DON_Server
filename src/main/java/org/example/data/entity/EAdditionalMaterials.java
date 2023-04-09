package org.example.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;


@Entity
@Table(name = "\"additional_materials\"")
public class EAdditionalMaterials implements Serializable {

    @Id
    @Column(name = "\"additional_materials_id\"")
    private Integer additional_materials_id;

    @Column(name = "\"audio\"")
    private byte[] audio;

    @Column(name = "\"video\"")
    private byte[] video;

    @Column(name = "\"transcription\"")
    private String transcription;

    public EAdditionalMaterials(){}

    public EAdditionalMaterials(Integer additional_materials_id, byte[] audio, byte[] video, String transcription){
        this.additional_materials_id = additional_materials_id;
        this.audio = audio;
        this.video = video;
        this.transcription = transcription;
    }

    public Integer getAdditional_materials_id() {
        return additional_materials_id;
    }

    public void setAdditional_materials_id(Integer additional_materials_id) {
        this.additional_materials_id = additional_materials_id;
    }

    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }
}
