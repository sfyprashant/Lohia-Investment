package com.Lohia.investment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.Lohia.investment.R;


public class SettingFragment extends Fragment {

    LinearLayout phoneLayout,whatsappLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_setting, container, false);

         phoneLayout = view.findViewById(R.id.phone_layout);
         whatsappLayout = view.findViewById(R.id.whatsapp_layout);

        phoneLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9389443599"));
                startActivity(callIntent);
            }
        });



        whatsappLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://wa.me/" + "9336578101"; // Constructing WhatsApp URL
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse(url));
                startActivity(whatsappIntent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}