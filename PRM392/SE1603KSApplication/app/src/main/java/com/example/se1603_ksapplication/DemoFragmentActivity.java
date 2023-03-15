package com.example.se1603_ksapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.se1603_ksapplication.fragment.DemoBlankFragment;
import com.example.se1603_ksapplication.fragment.PostListFragment;

public class DemoFragmentActivity extends AppCompatActivity {
    private DemoBlankFragment demoBlankFragment;
    private PostListFragment postListFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_fragment);
        demoBlankFragment = new DemoBlankFragment();
        postListFragment = PostListFragment.newInstance(1);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view_tag, postListFragment)//demoBlankFragment)
                .commit();
    }
}