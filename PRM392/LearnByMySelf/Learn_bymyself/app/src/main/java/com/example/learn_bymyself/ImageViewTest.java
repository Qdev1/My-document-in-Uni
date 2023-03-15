package com.example.learn_bymyself;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ImageViewTest extends AppCompatActivity {
ImageView imgHInh;
RelativeLayout manHinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        imgHInh = (ImageView) findViewById(R.id.imagineView1);
        manHinh = (RelativeLayout) findViewById(R.id.manHinh);
        imgHInh.setImageResource(R.drawable.rsz_1android);
        //manHinh.setBackgroundColor(Color.GREEN);
        manHinh.setBackgroundResource(R.drawable.background2);
    }
}