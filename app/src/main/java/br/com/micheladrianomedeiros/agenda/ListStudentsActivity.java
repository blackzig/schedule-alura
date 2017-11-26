package br.com.micheladrianomedeiros.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListStudentsActivity extends AppCompatActivity implements View.OnClickListener{

    Button go_form_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        String[] students = {"Michel", "Adriano", "Medeiros", "Angela", "Ferraz"};
        ListView listStudents = findViewById(R.id.list_students);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listStudents.setAdapter(adapter);

        go_form_student = findViewById(R.id.go_form_student);
        go_form_student.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.go_form_student:
                Intent intent = new Intent(this, FormStudentActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
