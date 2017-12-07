package br.com.micheladrianomedeiros.agenda.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.micheladrianomedeiros.agenda.model.Student;

/**
 * Created by zigui on 06/12/2017.
 */

public class StudentConverter {
    public String convertFromJson(List<Student> students) {
        JSONStringer jsonStringer = new JSONStringer();
        try {
            jsonStringer.object().key("list").array().object().key("student").array();
            for(Student student: students){
                jsonStringer.object();
                jsonStringer.key("name").value(student.getName());
                jsonStringer.key("assessment").value(student.getAssessment());
                jsonStringer.endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStringer.toString();
    }
}
