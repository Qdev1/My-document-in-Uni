package com.codeandroid.qlphongtro;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.codeandroid.qlphongtro.Fragment.NguoiThueFragment;
import com.codeandroid.qlphongtro.Fragment.PhongTroFragment;
import com.codeandroid.qlphongtro.Fragment.ThongKeFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new PhongTroFragment();
            case 1:
                return new ThongKeFragment();
            case 2:
                return new NguoiThueFragment();
            default:
                return new PhongTroFragment();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
