package br.com.micheladrianomedeiros.agenda;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;
import br.com.micheladrianomedeiros.agenda.model.Student;

public class ListStudentsActivity extends AppCompatActivity implements View.OnClickListener{

    Button go_form_student;
    ListView listStudents;
    EditText search_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);

        listStudents = findViewById(R.id.list_students);
        registerForContextMenu(listStudents);
        editStudent();

        go_form_student = findViewById(R.id.go_form_student);
        go_form_student.setOnClickListener(this);

        searchStudent();
    }

    private void searchStudent(){
        search_student = findViewById(R.id.search_student);
        search_student.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Log.i("beforeTextChanged>>> ", charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //  Log.i("onTextChanged>>> ", charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()>=5){
                    StudentDAO studentDAO = new StudentDAO(ListStudentsActivity.this);
                    String whatSearch = editable.toString();
                    List<Student> students = studentDAO.searchStudentFilter(whatSearch);
                    ArrayAdapter<Student> adapter = new ArrayAdapter<>(ListStudentsActivity.this, android.R.layout.simple_list_item_1, students);
                    listStudents.setAdapter(adapter);
                }
            }
        });
    }

    private void editStudent(){
        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = (Student) listStudents.getItemAtPosition(i);
                Intent intent = new Intent(ListStudentsActivity.this, FormStudentActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
    }

    private void loadList() {
        StudentDAO studentDAO = new StudentDAO(this);
        List<Student> students = studentDAO.searchStudent();

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        listStudents.setAdapter(adapter);

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

 /*   @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }*/

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem delete = menu.add("Remover");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) listStudents.getItemAtPosition(adapterContextMenuInfo.position);
                StudentDAO studentDAO = new StudentDAO(ListStudentsActivity.this);
                studentDAO.delete(student);
                //loadList();
                return false;
            }
        });
    }

}
