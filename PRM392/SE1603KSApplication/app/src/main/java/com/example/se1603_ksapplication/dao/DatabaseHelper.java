package com.example.se1603_ksapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.se1603_ksapplication.bean.UserInfo;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "UserData";
    private final static int VERSION = 1;
    private final static String USER_TABLE = "user_table";
    private final static String USER_NAME = "user_name";
    private final static String PASSWORD = "password";
    private final static String USER_ROLE = "role";
    private final static String CAMPUS = "campus";
    private final static String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE
            + "(" + USER_NAME + " TEXT NOT NULL PRIMARY KEY, " + PASSWORD + " TEXT NOT NULL,"
            + USER_ROLE + " TEXT, " +  CAMPUS + " TEXT)";
    private Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isOpen()) {
            db.execSQL(CREATE_USER_TABLE);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (db.isOpen()) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            db.execSQL(CREATE_USER_TABLE);
        }
    }
    public UserInfo select(String userName) {
        String selectUserSQL = "SELECT * FROM " + USER_TABLE + " WHERE " + USER_NAME + " =?";
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectUserSQL, new String[]{userName});
        if(cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo();
            int index = cursor.getColumnIndex(PASSWORD);
            userInfo.setPassword(cursor.getString(index));
            index = cursor.getColumnIndex(USER_ROLE);
            userInfo.setRole(cursor.getString(index));
            index = cursor.getColumnIndex(CAMPUS);
            userInfo.setCampus(cursor.getString(index));
            userInfo.setUserName(userName);
            return userInfo;
        }
        if (sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        return null;
    }
    public void insertUser(UserInfo userInfo) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, userInfo.getUserName());
        values.put(PASSWORD, userInfo.getPassword());
        values.put(USER_ROLE, userInfo.getRole());
        values.put(CAMPUS, userInfo.getCampus());
        sqLiteDatabase.insertOrThrow(USER_TABLE, null, values);
    }
}
