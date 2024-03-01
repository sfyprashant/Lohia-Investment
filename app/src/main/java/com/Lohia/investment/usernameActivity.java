package com.Lohia.investment;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.Lohia.investment.R;

public class usernameActivity extends AppCompatActivity {

    Button loginbtn;
    EditText username_var;
    EditText password_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_username);

        loginbtn = findViewById(R.id.loginbtn);

        username_var = (EditText) findViewById(R.id.username_text_field);
        password_var = (EditText) findViewById(R.id.password);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_ = username_var.getText().toString();
                String password_ = password_var.getText().toString();
//
//                if (!username_.isEmpty()) {
//                    username_var.setError(null);
////                    username_var.setErrorEnabled(false);
//                    if (!password_.isEmpty()) {
//                        password_var.setError(null);
//                        User_login_data user_login_data=new User_login_data(getApplicationContext());
//                        user_login_data.add_user(username_,password_);
//                        Toast.makeText(usernameActivity.this, "Done", Toast.LENGTH_SHORT).show();
//                    } else {
//                        password_var.setError("Please Enter the Password");
//                    }
//                } else {
//                    username_var.setError("Please Enter the Email");
//                }

            }
        });


    }
}