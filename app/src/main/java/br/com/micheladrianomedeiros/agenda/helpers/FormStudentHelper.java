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

    private Student student;

    public FormStudentHelper(FormStudentActivity formStudentActivity){
        name = formStudentActivity.findViewById(R.id.form_student_name);
        andress = formStudentActivity.findViewById(R.id.form_student_andress);
        fone = formStudentActivity.findViewById(R.id.form_student_fone);
        site = formStudentActivity.findViewById(R.id.form_student_site);
        assessment = formStudentActivity.findViewById(R.id.form_student_assessment);
        student = new Student();
    }

    public Student getStudent(){
        student.setName(name.getText().toString());
        student.setAndress(andress.getText().toString());
        student.setFone(fone.getText().toString());
        student.setSite(site.getText().toString());
        student.setAssessment(Double.valueOf(assessment.getProgress()));
        return student;
    }

    public void loadDataStudent(Student student) {
        name.setText(student.getName());
        andress.setText(student.getAndress());
        fone.setText(student.getFone());
        site.setText(student.getSite());
        assessment.setProgress(student.getAssessment().intValue());
        this.student = student;
    }
}
