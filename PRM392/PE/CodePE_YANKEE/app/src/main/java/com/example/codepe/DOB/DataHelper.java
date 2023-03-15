package com.example.codepe.DOB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Student_Manager";
    public Context context;
    public static final String Table_Name = "Student";
    public static final String Student_Id = "id";
    public static final String Student_Name = "name";
    public static final String Student_DOB = "birthday";
    public static final String Student_Core = "average_core";

    public DataHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " Create table "
                + Table_Name + " ( "
                + Student_Id + " INTEGER PRIMARY KEY, "
                + Student_Name + " TEXT, "
                + Student_DOB + " TEXT, "
                + Student_Core + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = " drop table if exists " + Table_Name;
        db.execSQL(query);
    }
    public void addStudent( String id,String name, String dob, String core){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Student_Id, id);
        content.put(Student_Name, name);
        content.put(Student_DOB, dob);
        content.put(Student_Core, core);

        long result = db.insert(Table_Name, null, content);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added success", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateStudent( String id,String name, String dob, String core){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(Student_Id, id);
        content.put(Student_Name, name);
        content.put(Student_DOB, dob);
        content.put(Student_Core, core);

        long result = db.insert(Table_Name, null, content);
        if(result == -1){
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData() {
        String query = "Select * from " + Table_Name;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
