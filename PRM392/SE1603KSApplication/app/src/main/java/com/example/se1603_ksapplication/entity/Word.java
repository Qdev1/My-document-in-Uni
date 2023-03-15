package com.example.se1603_ksapplication.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="word")
    private String word;

    @ColumnInfo(name = "description")
    private String description;
    public Word(@NonNull String word, String description) {
        this.word = word;
        this.description = description;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
