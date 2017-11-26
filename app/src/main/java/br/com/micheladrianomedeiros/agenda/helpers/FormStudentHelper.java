package br.com.micheladrianomedeiros.agenda.helpers;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.micheladrianomedeiros.agenda.FormStudentActivity;
import br.com.micheladrianomedeiros.agenda.R;
import br.com.micheladrianomedeiros.agenda.model.Student;

/**
 * Created by Michel on 26/11/2017.
 */

public class FormStudentHelper {

    EditText name, andress, fone, site;
    RatingBar assessment;

    public FormStudentHelper(FormStudentActivity formStudentActivity){
        name = formStudentActivity.findViewById(R.id.form_student_name);
        andress = formStudentActivity.findViewById(R.id.form_student_andress);
        fone = formStudentActivity.findViewById(R.id.form_student_fone);
        site = formStudentActivity.findViewById(R.id.form_student_site);
        assessment = formStudentActivity.findViewById(R.id.form_student_assessment);
    }

    public Student getStudent(){
        Student student = new Student();
        student.setName(name.getText().toString());
        student.setAndress(andress.getText().toString());
        student.setFone(andress.getText().toString());
        student.setSite(site.getText().toString());
        student.setAssessment(Double.valueOf(assessment.getProgress()));
        return student;
    }

}
