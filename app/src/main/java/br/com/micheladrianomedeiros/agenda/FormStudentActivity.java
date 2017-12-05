package br.com.micheladrianomedeiros.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.File;

import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;
import br.com.micheladrianomedeiros.agenda.helpers.FormStudentHelper;
import br.com.micheladrianomedeiros.agenda.model.Student;

public class FormStudentActivity extends AppCompatActivity{

    public static final int CODE_CAMERA = 567;
    private FormStudentHelper formStudentHelper;
    private String pathPhoto;

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
         //   Log.i("foto>>>>>", "Cadê a foto");
            formStudentHelper.loadDataStudent(student);
        }

        Button button_photo = findViewById(R.id.button_photo);
        button_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pathPhoto = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
              //  Log.i("button_photo ", pathPhoto);
                File archivePhoto = new File(pathPhoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(archivePhoto));
                startActivityForResult(intent, CODE_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==CODE_CAMERA){
                formStudentHelper.loadImage(pathPhoto);
            }
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
