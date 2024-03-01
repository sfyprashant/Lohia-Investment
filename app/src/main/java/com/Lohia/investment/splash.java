package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.Lohia.investment.R;

public class splash extends AppCompatActivity {

    ImageView logoImageView1,logoImageView2,logoImageView3,iamge2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        logoImageView1 = findViewById(R.id.gif_img);
        iamge2 = findViewById(R.id.iamge_two);
        Glide.with(this)
                .asGif()
                .load(R.drawable.lintro) // Replace 'your_gif_file' with the name of your GIF in the drawable folder
                .into(logoImageView1);
//        logoImageView2 = findViewById(R.id.logoImageView2);
//        logoImageView3 = findViewById(R.id.logoImageView3);
//
//        Animation slideLeftAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_left);
//        Animation slideRightAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_right);
//        Animation slideBottomAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_bottom);
//
//        logoImageView1.startAnimation(slideLeftAnimation);
//        logoImageView2.startAnimation(slideBottomAnimation);
//        logoImageView3.startAnimation(slideRightAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    SharedPreferences pre= getSharedPreferences("login",MODE_PRIVATE);
                    Boolean check= pre.getBoolean("flag",false);
                    Intent iHome;
                    if (check){
                        iHome= new Intent(splash.this, MainActivity.class );
                        finish();
                    }else{
                        iHome= new Intent(splash.this, signupActivity.class );
                        finish();
                    }
                    startActivity(iHome);
                    finish();

                    logoImageView1.setVisibility(View.GONE);
                    iamge2.setVisibility(View.VISIBLE);
                    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }catch (Exception e){
                    Toast.makeText(splash.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        }, 3500);
    }
}