package com.example.testview2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[]  items = {"HowKteams","Free Education","Share to be better"};

        listView = (ListView) listView.findViewById(R.id.list_viewS);
        TestAdapter adapter = new TestAdapter(this, items);
        listView.setAdapter(adapter);
    }
}