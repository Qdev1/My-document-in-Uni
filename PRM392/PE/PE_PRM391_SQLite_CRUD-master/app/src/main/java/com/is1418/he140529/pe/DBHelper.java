package com.is1418.he140529.pe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "Student";
    public static final String ID_COLUMN = "id";
    public static final String FULLNAME_COLUMN = "fullname";
    public static final String DOB_COLUMN = "dob";
    public static final String AVERAGE_SCORE_COLUMN = "averageScore";

    public DBHelper(Context context) {
        super(context, STUDENT_TABLE + "Data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table " + STUDENT_TABLE + "(" + ID_COLUMN + " INTEGER primary key, " + FULLNAME_COLUMN + " TEXT, " + DOB_COLUMN + " TEXT, " + AVERAGE_SCORE_COLUMN + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop table if exists " + STUDENT_TABLE);
        onCreate(DB);
    }

    public boolean insertStudent(StudentModel studentModel) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_COLUMN, studentModel.getId());
        contentValues.put(FULLNAME_COLUMN, studentModel.getFullName());
        contentValues.put(DOB_COLUMN, studentModel.getDob());
        contentValues.put(AVERAGE_SCORE_COLUMN, studentModel.getAverageScore());
        long result = DB.insert(STUDENT_TABLE, null, contentValues);
        DB.close();
        if (result == -1) {
            return false;
        }
        return true;
    }

    public void deleteStudent(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(STUDENT_TABLE, ID_COLUMN + " = ?",
                new String[]{String.valueOf(studentModel.getId())});
        db.close();
    }


    public boolean checkExistStudentInDB(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "select * from " + STUDENT_TABLE + " where " + ID_COLUMN + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public StudentModel getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(STUDENT_TABLE, new String[]{ID_COLUMN,
                        FULLNAME_COLUMN, DOB_COLUMN, AVERAGE_SCORE_COLUMN}, ID_COLUMN + "= ?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        int anInt = cursor.getInt(0);
        String string = cursor.getString(1);
        String string1 = cursor.getString(2);
        String string2 = cursor.getString(3);

        StudentModel studentModel = new StudentModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), Float.parseFloat(cursor.getString(3)));
        // return student
        return studentModel;
    }

    public List<StudentModel> getAllStudents() {
        List<StudentModel> studentModelList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + STUDENT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(Integer.parseInt(cursor.getString(0)));
                studentModel.setFullName(cursor.getString(1));
                studentModel.setDob(cursor.getString(2));
                studentModel.setAverageScore(Float.parseFloat(cursor.getString(3)));
                // Adding student to list
                studentModelList.add(studentModel);
            } while (cursor.moveToNext());
        }

        // return student list
        return studentModelList;
    }

    public List<StudentModel> searchStudents(ArrayList<String> arrSearch) {
        List<StudentModel> studentModelList = new ArrayList<>();
        // Select All Query
        String selectQuery = "select * from " + STUDENT_TABLE + " WHERE 1=1 ";

        selectQuery += " AND " + ID_COLUMN + " LIKE '%" + arrSearch.get(0) + "%'";
        selectQuery += " AND " + FULLNAME_COLUMN + " LIKE '%" + arrSearch.get(1) + "%'";
        selectQuery += " AND " + DOB_COLUMN + " LIKE '%" + arrSearch.get(2) + "%'";
        selectQuery += " AND " + AVERAGE_SCORE_COLUMN + " LIKE '%" + arrSearch.get(3) + "%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setId(Integer.parseInt(cursor.getString(0)));
                studentModel.setFullName(cursor.getString(1));
                studentModel.setDob(cursor.getString(2));
                studentModel.setAverageScore(Float.parseFloat(cursor.getString(3)));
                // Adding student to list
                studentModelList.add(studentModel);
            } while (cursor.moveToNext());
        }

        // return student list
        return studentModelList;
    }

    public int updateStudent(StudentModel studentModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FULLNAME_COLUMN, studentModel.getFullName());
        values.put(DOB_COLUMN, studentModel.getDob());
        values.put(AVERAGE_SCORE_COLUMN, studentModel.getAverageScore());

        // updating row
        return db.update(STUDENT_TABLE, values, ID_COLUMN + " = ?",
                new String[]{String.valueOf(studentModel.getId())});
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from " + STUDENT_TABLE, null);
        return cursor;
    }
}
