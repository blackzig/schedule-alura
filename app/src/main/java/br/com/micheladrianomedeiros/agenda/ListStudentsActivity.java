package br.com.micheladrianomedeiros.agenda;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Browser;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

import br.com.micheladrianomedeiros.agenda.adapter.StudentAdapter;
import br.com.micheladrianomedeiros.agenda.converter.StudentConverter;
import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;
import br.com.micheladrianomedeiros.agenda.model.Student;
import br.com.micheladrianomedeiros.agenda.tasks.SendStudentTask;
import br.com.micheladrianomedeiros.agenda.webservice.WebClient;

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
                if(editable.toString().length()>=3){
                    searchAgain();
                }
            }
        });
    }

    private void searchAgain(){
        StudentDAO studentDAO = new StudentDAO(ListStudentsActivity.this);
        List<Student> students = studentDAO.searchStudentFilter(search_student.getText().toString());
        StudentAdapter studentAdapter = new StudentAdapter(this, students);
        listStudents.setAdapter(studentAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String search = search_student.getText().toString();
        if(!search.equals("") || !search.isEmpty()){
            searchAgain();
        }
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Student student = (Student) listStudents.getItemAtPosition(adapterContextMenuInfo.position);

        MenuItem itemCall = menu.add("Ligar");
        itemCall.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(ActivityCompat.checkSelfPermission(ListStudentsActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListStudentsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 123);
                }else{
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:"+student.getFone()));
                    startActivity(intentCall);
                }
                return false;
            }
        });

        MenuItem sendSMS = menu.add("Enviar SMS");
        Intent intentSMS = new Intent(Intent.ACTION_VIEW);
        intentSMS.setData(Uri.parse("sms:"+student.getFone()));
        sendSMS.setIntent(intentSMS);

        MenuItem seeLocalization = menu.add("Endereço Mapa");
        Intent intentLocalization = new Intent(Intent.ACTION_VIEW);
        intentLocalization.setData(Uri.parse("geo:0.0?q="+student.getAndress()));
        seeLocalization.setIntent(intentLocalization);

        MenuItem goToSite = menu.add("Visitar site");
        Intent intent = new Intent(Intent.ACTION_VIEW);

        String site = student.getSite();
        if(site.isEmpty() || site==null){
            site="http://xnxx.com";
        }
        else if(!site.startsWith("http://")){
            site= "http://"+site;
        }

        intent.setData(Uri.parse(site));
        goToSite.setIntent(intent);

        MenuItem delete = menu.add("Remover");
        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                StudentDAO studentDAO = new StudentDAO(ListStudentsActivity.this);
                studentDAO.delete(student);
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send_assessments:
                new SendStudentTask(this).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
