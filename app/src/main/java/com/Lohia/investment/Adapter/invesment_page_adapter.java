package com.Lohia.investment.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class invesment_page_adapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;
    public invesment_page_adapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Add Fixed Deposits";
        } else if (position == 1){
            return "Fixed Deposits";
        }else if( position == 1 ){
            return "Mutual Funds";
        } else {
            return "Mutual Funds";
        }
    }
}
