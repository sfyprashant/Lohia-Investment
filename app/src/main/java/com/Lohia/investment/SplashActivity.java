package com.Lohia.investment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.Lohia.investment.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    startActivity(new Intent(SplashActivity.this, signupActivity.class ));
                    finish();
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){
                    Toast.makeText(SplashActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        }, 500);
    }
}