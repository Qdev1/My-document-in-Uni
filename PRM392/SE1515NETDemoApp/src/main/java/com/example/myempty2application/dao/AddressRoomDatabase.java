package com.example.myempty2application.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myempty2application.entity.Address;

@Database(entities = {Address.class}, version = 1)
public abstract class AddressRoomDatabase extends RoomDatabase {
    private final static String DATABASE_NAME = "AddressDatabase";
    public abstract AddressDAO addressDAO();
    private static AddressRoomDatabase INSTANCE;
    public static AddressRoomDatabase getDatabase(Context context) {
        synchronized (AddressRoomDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                AddressRoomDatabase.class, DATABASE_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
