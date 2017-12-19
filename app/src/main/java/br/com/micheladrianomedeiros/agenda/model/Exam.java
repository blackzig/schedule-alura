package br.com.micheladrianomedeiros.agenda.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zigui on 18/12/2017.
 */

public class Exam implements Serializable{

    private String class_;
    private String date;
    private List<String> topics;

    public Exam(String class_, String date, List<String> topics) {
        this.class_ = class_;
        this.date = date;
        this.topics = topics;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return this.class_;
    }
}
