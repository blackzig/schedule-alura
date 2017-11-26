package br.com.micheladrianomedeiros.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
