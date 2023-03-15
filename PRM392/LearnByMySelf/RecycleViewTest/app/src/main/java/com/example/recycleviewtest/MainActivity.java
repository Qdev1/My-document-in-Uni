package com.example.recycleviewtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recycleviewtest.model.Cat;
import com.example.recycleviewtest.model.CatAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView rcView;
private CatAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcView = findViewById(R.id.rview);
         adapter = new CatAdapter(getList(),this);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rcView.setLayoutManager(manager);
        rcView.setAdapter(adapter);
    }

    private List<Cat> getList(){
    List<Cat> list = new ArrayList<>();
    list.add(new Cat(R.drawable.cat1,"Meo 1"));
    list.add(new Cat(R.drawable.cat2,"Meo 2" ));
    list.add(new Cat(R.drawable.cat6,"Meo 6" ));
    return list;
    }
}