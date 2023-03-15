package com.codeandroid.qlphongtro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.codeandroid.qlphongtro.SQLHandle.SQLHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView btnNavi;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnNavi = findViewById(R.id.bottomNav);
        viewPager = findViewById(R.id.viewPager);

        setupViewPager();

        btnNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.support:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.support1:
                        viewPager.setCurrentItem(2);
                        break;

                }
                return true;
            }
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        btnNavi.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        btnNavi.getMenu().findItem(R.id.support).setChecked(true);
                        break;
                    case 2:
                        btnNavi.getMenu().findItem(R.id.support1).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}