package com.Lohia.investment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.Lohia.investment.Adapter.invesment_page_adapter;
import com.Lohia.investment.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MutualFundsFragment extends Fragment {
    Button insur;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mutual_funds, container, false);
        insur=view.findViewById(R.id.insuranceButton);
        try {
            insur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),MainActivity.class));
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(), "Something want wrong, Please try Later", Toast.LENGTH_SHORT).show();
        }

        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabMode);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FixedDepositsFragment());
        fragments.add(new FixedDeposits_show_Fragment());
        fragments.add(new mutualFragment());


        invesment_page_adapter adapter = new invesment_page_adapter(getParentFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }



}