package com.example.myempty2application.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myempty2application.fragment.ClosedProductListFragment;
import com.example.myempty2application.fragment.InactveProductListFragment;
import com.example.myempty2application.fragment.NewProductListFragment;

public class ProductListViewPagerAdapter extends FragmentPagerAdapter {
    private final static int NUM_PAGES = 3;
    public ProductListViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public ProductListViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NewProductListFragment();
                break;
            case 1:
                fragment = new InactveProductListFragment();
                break;
            case 2:
                fragment = new ClosedProductListFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return "New Products";
            case 1:
                return "Inactive Products";
            case 2:
                return "Closed Products";
            default:
                return "";
        }
    }
}
