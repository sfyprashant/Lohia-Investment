package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.Lohia.investment.R;
import com.Lohia.investment.classes.FileDownloader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Car_showActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView policytype,vehiclcategory,premium,vehicalnumber,type,companyname,policynumber,make,channel,policyenddata,policystartdate;
    Button pay,download;
    ImageView back;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    String payurl,receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_show);
        policytype=findViewById(R.id.car_show_text_one);
        vehiclcategory=findViewById(R.id.car_show_text_four);
        vehicalnumber=findViewById(R.id.car_show_text_six);
        type=findViewById(R.id.car_show_text_two);
        companyname=findViewById(R.id.car_show_text_nine);
        policynumber=findViewById(R.id.car_show_text_three);
        premium=findViewById(R.id.car_show_text_five);
        make=findViewById(R.id.car_show_text_seven);
        policyenddata=findViewById(R.id.car_show_text_eleven);
        policystartdate=findViewById(R.id.car_show_text_ten);
        download = findViewById(R.id.car_show_download);
        back = findViewById(R.id.backbtn);


        try{
            back.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), CarDataActivity.class));
                finish();
            });
        }catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }


        pay=findViewById(R.id.car_show_pay);
        try{
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),PaymentSuccessActivity.class));
            }
        });
        }catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }


        int INDEX = getIntent().getIntExtra("rootcheck",-3);
        if (INDEX==-1){
            try {
                CollectionReference usersCollection = db.collection("users");
                SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
                String num = pre.getString("num", "No Data");
                DocumentReference userDocument = usersCollection.document(num);
                userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DocumentSnapshot document = task.getResult();
                         if (document.exists()) {
                             Map<String, Object> data = document.getData();
                             ArrayList<Map<String, Object>> carList = (ArrayList<Map<String, Object>>) data.get("car_policy");
                             int position = getIntent().getIntExtra("position", -1);
                             Map<String, Object> carData = carList.get(position);
                             if (carData != null) {
                                 policytype.setText((String) carData.get("policyfrom"));
                                 vehiclcategory.setText((String) carData.get("vehiclecategory"));
                                 vehicalnumber.setText((String) carData.get("vehiclenumber"));
                                 type.setText((String) carData.get("types"));
                                 companyname.setText((String) carData.get("companyname"));
                                 policynumber.setText((String) carData.get("policynumber"));
                                 premium.setText((String) carData.get("premium"));
                                 make.setText((String) carData.get("model"));
                                 policystartdate.setText((String) carData.get("startdate"));
                                 policyenddata.setText((String) carData.get("enddate"));
                                 payurl=(String) carData.get("paylink");
                                 receipt=(String) carData.get("receipt_url");

                             }
                         }
                     }
                 }
                }
                );

            }catch (Exception e){
                Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
        }else {
            try {
                CollectionReference usersCollection = db.collection("users");
                SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
                String num = pre.getString("num", "No Data");
                DocumentReference userDocument = usersCollection.document(num);
                userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
             @Override
             public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                 if (task.isSuccessful()) {
                     DocumentSnapshot document = task.getResult();
                     if (document.exists()) {
                         Map<String, Object> data = document.getData();
                         ArrayList<Map<String, Object>> childList = (ArrayList<Map<String, Object>>) data.get("children");
                         if (childList != null && childList.size() > 0) {
                             Map<String, Object> childData = childList.get(INDEX);
                             ArrayList<Map<String, Object>> carList = (ArrayList<Map<String, Object>>) childData.get("car_policy");
                             int position = getIntent().getIntExtra("position", -1);
                             Map<String, Object> carData = carList.get(position);
                             if (carData != null) {
                                 policytype.setText((String) carData.get("policyfrom"));
                                 vehiclcategory.setText((String) carData.get("vehiclecategory"));
                                 vehicalnumber.setText((String) carData.get("vehiclenumber"));
                                 type.setText((String) carData.get("types"));
                                 companyname.setText((String) carData.get("companyname"));
                                 policynumber.setText((String) carData.get("policynumber"));
                                 premium.setText((String) carData.get("premium"));
                                 make.setText((String) carData.get("model"));
                                 policystartdate.setText((String) carData.get("startdate"));
                                 policyenddata.setText((String) carData.get("enddate"));
                                 payurl=(String) carData.get("paylink");
                                 receipt=(String) carData.get("receipt_url");
                             }
                         }
                     }
                 }
             }
         });


            }catch (Exception e){
                Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
        }

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileDownloader.downloadFile(receipt,getApplicationContext());
            }
        });
        pay=findViewById(R.id.car_show_pay);
        try{
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent url= new Intent();
                    url.setAction(Intent.ACTION_VIEW);
                    url.addCategory(Intent.CATEGORY_BROWSABLE);
                    url.setData(Uri.parse(payurl));
                    startActivity(url);
                }
            });
        }catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }
        drawerLayout = findViewById(R.id.drawerLayout2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_item2_1) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finishAffinity();

        } else if (id == R.id.nav_item2_2) {
            // Handle item 2 click
            startActivity(new Intent(getApplicationContext(),signupActivity.class));
            finishAffinity();
            SharedPreferences pre=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor=pre.edit();
            editor.putBoolean("flag",false);
            editor.apply();

        }
        // Close the drawer after handling the item click
        DrawerLayout drawer = findViewById(R.id.drawerLayout2);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

}