package com.example.se1603_ksapplication.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.se1603_ksapplication.entity.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {
    public abstract WordDAO wordDAO();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getDatabase(final Context context) {
        synchronized (WordRoomDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                WordRoomDatabase.class, "Word_Database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
