package com.example.se1603_ksapplication.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.se1603_ksapplication.entity.Word;

import java.util.List;

@Dao
public interface WordDAO {
    @Query("select * from word_table")
    List<Word> getAllWords();

    @Query("select * from word_table w where w.word like :word")
    List<Word> select(String word);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Update
    void update(Word ... words);
}
