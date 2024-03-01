package com.Lohia.investment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.Lohia.investment.R;


public class No_Policy_Page extends AppCompatActivity {

//    TextView call;
    Button home;
    RelativeLayout whatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_policy_page);
        whatsapp = findViewById(R.id.click);
        int drawableResourceId = getIntent().getIntExtra("IMG", 0);
        if (drawableResourceId != 0) {
            Drawable drawable = getResources().getDrawable(drawableResourceId);
            whatsapp.setBackground(drawable);
        } else {
            Drawable drawable2 = getResources().getDrawable(R.drawable.health);
            whatsapp.setBackground(drawable2);

//            Toast.makeText(this, "IMAGE NOT COME", Toast.LENGTH_SHORT).show();
        }
        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://wa.me/" + "9336578101"; // Constructing WhatsApp URL
                Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                whatsappIntent.setData(Uri.parse(url));
                startActivity(whatsappIntent);
            }
        });

        home= findViewById(R.id.home);
        home.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));
    }
}