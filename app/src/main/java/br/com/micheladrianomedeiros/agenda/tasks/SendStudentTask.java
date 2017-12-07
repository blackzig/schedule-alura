package br.com.micheladrianomedeiros.agenda.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.micheladrianomedeiros.agenda.converter.StudentConverter;
import br.com.micheladrianomedeiros.agenda.dao.StudentDAO;
import br.com.micheladrianomedeiros.agenda.model.Student;
import br.com.micheladrianomedeiros.agenda.webservice.WebClient;

/**
 * Created by zigui on 07/12/2017.
 */

public class SendStudentTask extends AsyncTask<Void, Void, String>{
    private Context context;
    private ProgressDialog progressDialog;

    public SendStudentTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde", "Enviando notas...", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        StudentDAO studentDAO = new StudentDAO(context);
        List<Student> students = studentDAO.allStudent();

        StudentConverter studetConverter = new StudentConverter();
        String json = studetConverter.convertFromJson(students);


        WebClient webClient = new WebClient();
        String answer = webClient.post(json);

        return answer;
    }

    @Override
    protected void onPostExecute(String answer) {
        progressDialog.dismiss();
        Toast.makeText(context, answer, Toast.LENGTH_LONG).show();
    }
}
