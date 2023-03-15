package com.example.se1603_ksapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.se1603_ksapplication.adapter.WordListAdapter;
import com.example.se1603_ksapplication.common.IntentKeys;
import com.example.se1603_ksapplication.entity.Word;
import com.example.se1603_ksapplication.repository.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordListActivity extends AppCompatActivity {
    private List<String> wordList = new ArrayList<>();
    private WordListAdapter wordListAdapter;
    private WordRepository wordRepository = null;
    //Define a object of ActivityResultLauncher which is used to start an activity for get data back
    private ActivityResultLauncher<Intent> mStartActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 2) {
                        //Intent data = result.getData();
                        List<Word> wordListTemp = wordRepository.getAllWords();
                        wordList.clear();
                        for(int i = 0; i < wordListTemp.size(); i++) {
                            wordList.add(wordListTemp.get(i).getWord());
                        }
                    }
                    wordListAdapter.notifyDataSetChanged(); //method used to update RecycleView
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        wordRepository = new WordRepository(this);
        wordListAdapter = new WordListAdapter(this, getWordList());
        recyclerView.setAdapter(wordListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //wordListAdapter.notifyDataSetChanged(); method used to update RecycleView
        Button button = findViewById(R.id.bt_add_word);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordListActivity.this, AddNewWordActivity.class);
                mStartActivity.launch(intent); // New way to start an activity to get data back
            }
        });
    }
    private List getWordList() {
//        for(int i = 0; i <= 50; i++) {
//            wordList.add("Word " + i);
//        }
//        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.words));
//        while(scanner.hasNext()){
//            String word = scanner.nextLine();
//            wordList.add(word);
//        }
//        scanner.close();


        List<Word> wordListTemp = wordRepository.getAllWords();
        if (wordListTemp == null || wordListTemp.size() == 0) {
            Word word = new Word("Test 1", "Test 12233");
            wordRepository.insertWord(word);
        }
        for(int i = 0; i < wordListTemp.size(); i++) {
            wordList.add(wordListTemp.get(i).getWord());
        }
        return wordList;
    }
}