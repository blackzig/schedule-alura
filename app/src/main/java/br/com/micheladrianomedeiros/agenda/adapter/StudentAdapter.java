package br.com.micheladrianomedeiros.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.micheladrianomedeiros.agenda.ListStudentsActivity;
import br.com.micheladrianomedeiros.agenda.R;
import br.com.micheladrianomedeiros.agenda.model.Student;

/**
 * Created by zigui on 06/12/2017.
 */

public class StudentAdapter extends BaseAdapter{

    private final List<Student> students;
    private final Context context;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return students.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Student student = students.get(i);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View textView = view;
        if(view==null){
            textView = layoutInflater.inflate(R.layout.list_item, viewGroup, false);
        }

        TextView name = textView.findViewById(R.id.name_student);
        name.setText(student.getName());
        TextView fone = textView.findViewById(R.id.fone_student);
        fone.setText(student.getFone());

        TextView andress = textView.findViewById(R.id.andress_student);
        if(andress!=null){
            andress.setText(student.getAndress());
        }

        TextView site = textView.findViewById(R.id.site_student);
        if(site!=null){
            site.setText(student.getSite());
        }

        ImageView imageView =  textView.findViewById(R.id.photo_student);
        String pathPhoto = student.getPhoto();

        if(pathPhoto!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapMinor = Bitmap.createScaledBitmap(bitmap, 100,100,true);
            imageView.setImageBitmap(bitmapMinor);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        return textView;
    }
}
