package com.Lohia.investment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

    EditText mobile;
    Button getotp;
    String DOCUMENT_KEY;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile =  findViewById(R.id.mobile);
        getotp = findViewById(R.id.getotp);
        final ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkAvailable()) {
                    // Internet is available
                    try {
                        if(!mobile.getText().toString().trim().isEmpty()){
                            if ((mobile.getText().toString().trim()).length() == 10) {
                                CollectionReference collectionRef = db.collection("users");
                                DocumentReference userDocument = collectionRef.document(mobile.getText().toString());
                                userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document=task.getResult();
                                            if (document.exists()){
                                                progressBar.setVisibility(View.VISIBLE);
                                                getotp.setVisibility(View.INVISIBLE);
                                                SharedPreferences pre=getSharedPreferences("login",MODE_PRIVATE);
                                                SharedPreferences.Editor editor=pre.edit();
                                                editor.putString("num",mobile.getText().toString());
                                                editor.apply();

                                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                                startActivity(intent);
                                                finishAffinity();
//                                            PhoneAuthProvider phoneAuthProvider = PhoneAuthProvider.getInstance();
//                                            phoneAuthProvider.verifyPhoneNumber(
//                                                    "+91" + mobile.getText().toString(),
//                                                    60,
//                                                    TimeUnit.SECONDS,
//                                                    LoginActivity.this,
//                                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                                        @Override
//                                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                                                            progressBar.setVisibility(View.GONE);
//                                                            getotp.setVisibility(View.VISIBLE);
//                                                        }
//
//                                                        @Override
//                                                        public void onVerificationFailed(@NonNull FirebaseException e) {
//                                                            progressBar.setVisibility(View.GONE);
//                                                            getotp.setVisibility(View.VISIBLE);
//                                                            Toast.makeText(LoginActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
//                                                        }
//
//                                                        @Override
//                                                        public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                                                            //                                        super.onCodeSent(s, forceResendingToken);
//                                                            progressBar.setVisibility(View.GONE);
//                                                            getotp.setVisibility(View.VISIBLE);
//                                                            Intent intent = new Intent(getApplicationContext(), otpfill.class);
//                                                            intent.putExtra("mobile", mobile.getText().toString());
//                                                            intent.putExtra("backendotp",backendotp);
//                                                            startActivity(intent);
//                                                        }
//                                                    }
//                                            );
                                            }else{
                                                Toast.makeText(LoginActivity.this,"Please Enter Registered Number",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });

                            }else{
                                Toast.makeText(LoginActivity.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"Enter Mobile Number",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        Toast.makeText(LoginActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Check Your Internet", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
