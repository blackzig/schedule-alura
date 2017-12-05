package br.com.micheladrianomedeiros.agenda.helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView no_photo;

    private Student student;

    public FormStudentHelper(FormStudentActivity formStudentActivity){
        name = formStudentActivity.findViewById(R.id.form_student_name);
        andress = formStudentActivity.findViewById(R.id.form_student_andress);
        fone = formStudentActivity.findViewById(R.id.form_student_fone);
        site = formStudentActivity.findViewById(R.id.form_student_site);
        assessment = formStudentActivity.findViewById(R.id.form_student_assessment);
        no_photo = formStudentActivity.findViewById(R.id.no_photo);
        student = new Student();
    }

    public Student getStudent(){
        student.setName(name.getText().toString());
        student.setAndress(andress.getText().toString());
        student.setFone(fone.getText().toString());
        student.setSite(site.getText().toString());
        student.setAssessment(Double.valueOf(assessment.getProgress()));
        student.setPhoto((String) no_photo.getTag());
        return student;
    }

    public void loadDataStudent(Student student) {
        name.setText(student.getName());
        andress.setText(student.getAndress());
        fone.setText(student.getFone());
        site.setText(student.getSite());
        assessment.setProgress(student.getAssessment().intValue());
        //Log.i("loadDataStudent>>> ", student.getPhoto());
        loadImage(student.getPhoto());
        this.student = student;
    }

    public void loadImage(String pathPhoto) {
        //Log.i("loadImage>>>>>>>>>>>> ", String.valueOf(pathPhoto));
        if(pathPhoto!=null){
           // Log.i("loadImage ","EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapMinor = Bitmap.createScaledBitmap(bitmap, 300,300,true);
            no_photo.setImageBitmap(bitmapMinor);
            no_photo.setScaleType(ImageView.ScaleType.FIT_XY);
            no_photo.setTag(pathPhoto);
        }
    }
}
