package br.com.micheladrianomedeiros.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.micheladrianomedeiros.agenda.model.Student;

/**
 * Created by Michel on 26/11/2017.
 */

public class StudentDAO extends SQLiteOpenHelper{


    public StudentDAO(Context context) {
        super(context, "Schedule", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE STUDENT " +
                "(id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "andress TEXT, " +
                "fone TEXT, " +
                "site TEXT, " +
                "assessment REAL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Schedule";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = dataFromFields(student);
        db.insert("STUDENT",null,contentValues);
        db.close();
    }

    private ContentValues dataFromFields(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("andress", student.getAndress());
        contentValues.put("fone", student.getFone());
        contentValues.put("site", student.getSite());
        contentValues.put("assessment", student.getAssessment());
        return contentValues;
    }

    public List<Student> searchStudent() {
        String sql = "SELECT * FROM STUDENT";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Student> students = new ArrayList<>();
        while(c.moveToNext()){
            Student student = new Student();
            student.setId(c.getLong(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAndress(c.getString(c.getColumnIndex("andress")));
            student.setFone(c.getString(c.getColumnIndex("fone")));
            student.setSite(c.getString(c.getColumnIndex("site")));
            student.setAssessment(c.getDouble(c.getColumnIndex("assessment")));
            students.add(student);
        }
        c.close();
        sqLiteDatabase.close();
        return students;
    }

    public List<Student> searchStudentFilter(String whatSearch) {
        String sql = "SELECT * FROM STUDENT where name like '%"+whatSearch+"%'";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(sql,null);

        List<Student> students = new ArrayList<>();
        while(c.moveToNext()){
            Student student = new Student();
            student.setId(c.getLong(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setAndress(c.getString(c.getColumnIndex("andress")));
            student.setFone(c.getString(c.getColumnIndex("fone")));
            student.setSite(c.getString(c.getColumnIndex("site")));
            student.setAssessment(c.getDouble(c.getColumnIndex("assessment")));
            students.add(student);
        }
        c.close();
        sqLiteDatabase.close();
        return students;
    }

    public void delete(Student student) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String[] params = {student.getId().toString()};
        sqLiteDatabase.delete("STUDENT", "id=?", params);
        sqLiteDatabase.close();
    }

    public void update(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = dataFromFields(student);
        String[] params = {student.getId().toString()};
        db.update("STUDENT", contentValues, "id=?", params);
    }
}
