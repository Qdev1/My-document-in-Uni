package com.example.se1603_ksapplication.repository;

import android.app.Application;
import android.content.Context;

import com.example.se1603_ksapplication.dao.WordDAO;
import com.example.se1603_ksapplication.dao.WordRoomDatabase;
import com.example.se1603_ksapplication.entity.Word;

import java.util.List;

public class WordRepository {
    private WordDAO wordDAO;
    //private WordRoomDatabase wordRoomDatabase;
    public WordRepository(Context context) {
       // this.wordRoomDatabase = WordRoomDatabase.getDatabase(application);
        wordDAO = WordRoomDatabase.getDatabase(context).wordDAO();
    }
    public List<Word> getAllWords() {
        return wordDAO.getAllWords();
    }
    public void insertWord(Word word) {
        wordDAO.insert(word);
    }
}
