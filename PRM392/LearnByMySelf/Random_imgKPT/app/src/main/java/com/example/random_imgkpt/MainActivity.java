package com.example.random_imgkpt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinearLayout linear;
    ArrayList<Integer> arrayImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = (LinearLayout) findViewById(R.id.myLinerLayout);
        arrayImg = new ArrayList<>();
        arrayImg.add(R.drawable.bg1);
        arrayImg.add(R.drawable.bg2);
        arrayImg.add(R.drawable.bg3);
        arrayImg.add(R.drawable.bg4);
        arrayImg.add(R.drawable.bg5);

        Random ran = new Random();
        int vitri = ran.nextInt(arrayImg.size());
        linear.setBackgroundResource(arrayImg.get(vitri));
    }
}