package com.example.myempty2application.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myempty2application.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static String DATABASE = "SampleDB";
    private final static String USER_TABLE = "user_table";
    private final static String ACCOUNT_COLUMN = "account";
    private final static String FULL_NAME_COLUMN = "full_name";
    private final static String PASSWORD_COLUMN = "password";
    private final static String PHONE_COLUMN = "phone";
    private final static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "(" + ACCOUNT_COLUMN + " TEXT PRIMARY KEY, "
                + FULL_NAME_COLUMN + " TEXT NOT NULL, "
                + PHONE_COLUMN + " TEXT, "
                + PASSWORD_COLUMN + " TEXT)";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Insert an user info into user_table in database
     *
     * @param fullName
     * @param account
     * @param phone
     * @param password
     */
    public void insertUser(String fullName, String account, String phone, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FULL_NAME_COLUMN, fullName);
        contentValues.put(ACCOUNT_COLUMN, account);
        contentValues.put(PHONE_COLUMN, phone);
        contentValues.put(PASSWORD_COLUMN, password);
        sqLiteDatabase.insertOrThrow(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<UserInfo> getAllUsers() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sqlSelect = "SELECT * FROM " + USER_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
        List<UserInfo> userInfoList = new ArrayList<>();
        while (cursor.moveToNext()) {
            UserInfo userInfo = new UserInfo();
            int columnIndex = cursor.getColumnIndex(FULL_NAME_COLUMN);
            userInfo.setFullName(cursor.getString(columnIndex));
            //Get data from Account Column
            columnIndex = cursor.getColumnIndex(ACCOUNT_COLUMN);
            userInfo.setAccount(cursor.getString(columnIndex));
            //
            columnIndex = cursor.getColumnIndex(PHONE_COLUMN);
            userInfo.setPhone(cursor.getString(columnIndex));
            //
            columnIndex = cursor.getColumnIndex(PASSWORD_COLUMN);
            userInfo.setPassword(cursor.getString(columnIndex));
            userInfoList.add(userInfo);
        }
        cursor.close();
        return userInfoList;
    }

    public UserInfo getUserInfo(String account) {
        SQLiteDatabase sqLiteDatabase = null;
        UserInfo userInfo = null;
        try {
            sqLiteDatabase = getReadableDatabase();
            String sqlSelect = "SELECT * FROM " + USER_TABLE + " WHERE " + ACCOUNT_COLUMN + " =?";
            Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, new String[]{account});
            while (cursor.moveToNext()) {
                userInfo = new UserInfo();
                int columnIndex = cursor.getColumnIndex(FULL_NAME_COLUMN);
                userInfo.setFullName(cursor.getString(columnIndex));
                //Get data from Account Column
                columnIndex = cursor.getColumnIndex(ACCOUNT_COLUMN);
                userInfo.setAccount(cursor.getString(columnIndex));
                //
                columnIndex = cursor.getColumnIndex(PHONE_COLUMN);
                userInfo.setPhone(cursor.getString(columnIndex));
                //
                columnIndex = cursor.getColumnIndex(PASSWORD_COLUMN);
                userInfo.setPassword(cursor.getString(columnIndex));
            }
            cursor.close();
        } catch (Exception exception) {
            throw exception;
        } finally {
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
        }
        return userInfo;
    }

    /**
     * Insert a record into user_table
     *
     * @param contentValues
     */
    public String insertUser(ContentValues contentValues) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insertOrThrow(USER_TABLE, null, contentValues);
        sqLiteDatabase.close();
        return contentValues.getAsString(ACCOUNT_COLUMN);
    }

    /**
     * Get all user records from user_table
     *
     * @return
     */
    public Cursor getAllUserInfo() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sqlSelect = "SELECT * FROM " + USER_TABLE;
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, null);
        return cursor;
    }

    public Cursor getOneUserInfo(String account) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sqlSelect = "SELECT * FROM " + USER_TABLE + " WHERE " + ACCOUNT_COLUMN + " =?";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect, new String[]{account});
        return cursor;
    }
}
