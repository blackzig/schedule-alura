package br.com.micheladrianomedeiros.agenda.model;

import java.io.Serializable;

/**
 * Created by Michel on 26/11/2017.
 */

public class Student implements Serializable{

    private Long id;
    private String name;
    private String andress;
    private String fone;
    private String site;
    private Double assessment;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAndress() {
        return andress;
    }

    public void setAndress(String andress) {
        this.andress = andress;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getAssessment() {
        return assessment;
    }

    public void setAssessment(Double assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return getId() + " - " + getName();
    }
}
