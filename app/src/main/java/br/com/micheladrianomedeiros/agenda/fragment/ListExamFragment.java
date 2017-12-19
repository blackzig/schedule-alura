package br.com.micheladrianomedeiros.agenda.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import br.com.micheladrianomedeiros.agenda.ExamActivity;
import br.com.micheladrianomedeiros.agenda.ExamDetailActivity;
import br.com.micheladrianomedeiros.agenda.R;
import br.com.micheladrianomedeiros.agenda.model.Exam;

/**
 * Created by zigui on 18/12/2017.
 */

public class ListExamFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_exam, container, false);
        List<String> topicsPortuguese = Arrays.asList("Sujeito", "Objeto Direto", "Objeto Indireto");

        Exam ep = new Exam("Português", "19/12/2017", topicsPortuguese);

        List<String> topicsMath = Arrays.asList("Equações do Segundo Grau", "Trigonometria");
        Exam em = new Exam("Matemática", "20/12/2017", topicsMath);

        List<Exam> exams = Arrays.asList(ep, em);
        ArrayAdapter<Exam> a = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, exams);

        ListView list = v.findViewById(R.id.list_exam);
        list.setAdapter(a);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Exam e = (Exam) adapterView.getItemAtPosition(i);
                ExamActivity ea = (ExamActivity) getActivity();
                ea.selectExam(e);
            }
        });
        return v;
    }
}
