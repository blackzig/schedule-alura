package br.com.micheladrianomedeiros.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;
import br.com.micheladrianomedeiros.agenda.helpers.FormStudentHelper;
import br.com.micheladrianomedeiros.agenda.model.Student;

public class FormStudentActivity extends AppCompatActivity{

    private FormStudentHelper formStudentHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        formStudentHelper = new FormStudentHelper(this);
        deserializableStudent();
    }

    private void deserializableStudent(){
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("student");
        if(student!=null){
            formStudentHelper.loadDataStudent(student);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_form_add_student, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem i) {
        switch (i.getItemId()){
            case R.id.ok_add_student:
                Student student = formStudentHelper.getStudent();
                StudentDAO studentDAO = new StudentDAO(this);
                if(student.getId()==null){
                    studentDAO.insert(student);
                    Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
                }else{
                    studentDAO.update(student);
                    Toast.makeText(this, "Atualizado", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            default:
        }
        return super.onOptionsItemSelected(i);
    }
}
