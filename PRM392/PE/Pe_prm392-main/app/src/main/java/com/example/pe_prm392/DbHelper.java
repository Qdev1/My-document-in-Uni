package com.example.pe_prm392;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context,
                        @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, "PePrm", factory, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = "CREATE TABLE Job(Id TEXT PRIMARY KEY," +
               "name TEXT, status TEXT, description TEXT)";
       db.execSQL(sql);
   }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            db.execSQL("DROP TABLE IF EXISTS Job");
            onCreate(db);
        }
    }
}
