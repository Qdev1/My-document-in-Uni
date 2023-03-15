package com.example.myempty2application;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myempty2application.fragment.PostListFragment;

public class PostListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view_tag, new PostListFragment())
                .setReorderingAllowed(true)
                .commit();
    }
}