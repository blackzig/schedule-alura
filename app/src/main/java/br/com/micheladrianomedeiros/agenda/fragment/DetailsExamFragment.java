package br.com.micheladrianomedeiros.agenda.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import br.com.micheladrianomedeiros.agenda.R;
import br.com.micheladrianomedeiros.agenda.model.Exam;

public class DetailsExamFragment extends Fragment {

    private TextView detail_class, detail_date;
    private ListView list_topics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_details_exam, container, false);

        detail_class = v.findViewById(R.id.detail_class);
        detail_date = v.findViewById(R.id.detail_date);
        list_topics = v.findViewById(R.id.list_topics);

        Bundle parameters = getArguments();
        if(parameters!=null){
            Exam exam = (Exam) parameters.getSerializable("exam");
            loadFields(exam);
        }

        return v;
    }

    public void loadFields(Exam exam){
        detail_class.setText(exam.getClass_());
        detail_date.setText(exam.getDate());

        ArrayAdapter<String> adapterTopics = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, exam.getTopics());
        list_topics.setAdapter(adapterTopics);
    }

}
