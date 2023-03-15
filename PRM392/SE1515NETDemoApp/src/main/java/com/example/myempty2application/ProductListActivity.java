package com.example.myempty2application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.myempty2application.adapter.ProductListViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setTitle("Product List");
        setSupportActionBar(toolbar);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_page);
        viewPager.setAdapter(new ProductListViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}