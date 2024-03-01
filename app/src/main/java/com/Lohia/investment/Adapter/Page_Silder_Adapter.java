package com.Lohia.investment.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class Page_Silder_Adapter extends FragmentPagerAdapter {
    private final List<Fragment> fragments;
public Page_Silder_Adapter(FragmentManager fragmentManager, List<Fragment> fragments) {
    super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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
            return "Add Health Policy";
        } else if (position == 1) {
            return "Add Motor Policy";
        }else{
            return "Add Life Policy";
        }

    }
}
