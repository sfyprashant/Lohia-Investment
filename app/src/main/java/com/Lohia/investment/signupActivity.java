package com.Lohia.investment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.Lohia.investment.R;

public class signupActivity extends AppCompatActivity {

    Button loginmobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginmobile = findViewById(R.id.loginviamobile);



        try {
            loginmobile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
//                        startActivity(new Intent(signupActivity.this, MainActivity.class));
                    startActivity(new Intent(signupActivity.this, LoginActivity.class));
                    } catch (Exception e) {
                        e.printStackTrace();
                        // Handle any potential exceptions here
                        startActivity(new Intent(signupActivity.this, MainActivity.class));
                    }
                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Something want wrong, Please try latter", Toast.LENGTH_SHORT).show();
        }
       
    }
}