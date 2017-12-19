package br.com.micheladrianomedeiros.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

import br.com.micheladrianomedeiros.agenda.model.Exam;

public class ExamDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);

        Intent i = getIntent();
        Exam e = (Exam) i.getSerializableExtra("exam");

        TextView class_ = findViewById(R.id.detail_class);
        TextView date = findViewById(R.id.detail_date);
        ListView listTopics = findViewById(R.id.list_topics);

        class_.setText(e.getClass_());
        date.setText(e.getDate());

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, e.getTopics());
        listTopics.setAdapter(a);

    }
}
