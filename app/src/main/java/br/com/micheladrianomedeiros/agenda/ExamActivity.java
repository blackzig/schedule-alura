package br.com.micheladrianomedeiros.agenda;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.micheladrianomedeiros.agenda.fragment.DetailsExamFragment;
import br.com.micheladrianomedeiros.agenda.fragment.ListExamFragment;
import br.com.micheladrianomedeiros.agenda.model.Exam;

public class ExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.principal_frame, new ListExamFragment());
        if(isModeLand()){
            ft.replace(R.id.secondary_frame, new DetailsExamFragment());
        }
        ft.commit();
    }

    private boolean isModeLand() {
        return getResources().getBoolean(R.bool.modeLand);
    }

    public void selectExam(Exam e) {
        FragmentManager fm = getSupportFragmentManager();
        if(!isModeLand()){
            FragmentTransaction ft = fm.beginTransaction();

            DetailsExamFragment def = new DetailsExamFragment();
            Bundle parameters = new Bundle();
            parameters.putSerializable("exam", e);
            def.setArguments(parameters);

            ft.replace(R.id.principal_frame, def);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            DetailsExamFragment def = (DetailsExamFragment) fm.findFragmentById(R.id.secondary_frame);
            def.loadFields(e);
        }
    }
}
