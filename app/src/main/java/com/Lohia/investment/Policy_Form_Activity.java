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

public class Policy_Form_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    TextView title,policyform,policytype,policynumber,firstpolicynumber,gst,suma,ttd,firstpolicydate,policystartdate,policyenddate;
    Button pay ,download;
    ImageView back;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    String payurl,receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_form);
        back=findViewById(R.id.backbtn);
        download=findViewById(R.id.health_download);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UserDataActivity.class));
                finish();
            }
        });
        pay=findViewById(R.id.policy_form_pay_now);
        policyform=findViewById(R.id.policy_form_text_field_one);
        policytype=findViewById(R.id.ploicy_form_text_field_two);
        policynumber=findViewById(R.id.policy_form_text_field_three);
        firstpolicynumber=findViewById(R.id.policy_form_text_field_four);
        gst=findViewById(R.id.policy_form_text_field_five);
        suma=findViewById(R.id.policy_form_text_field_six);
        ttd=findViewById(R.id.policy_form_text_field_seven);
        firstpolicydate=findViewById(R.id.policy_form_text_field_eight);
        policystartdate=findViewById(R.id.policy_form_text_field_nine);
        policyenddate=findViewById(R.id.policy_form_text_field_ten);

        CollectionReference usersCollection = db.collection("users");
        SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
        String num = pre.getString("num", "No Data");
        DocumentReference userDocument = usersCollection.document(num);
        int INDEX = getIntent().getIntExtra("rootcheck",-3);
        if (INDEX==-1){
            try{
                userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                         if (task.isSuccessful()) {
                             DocumentSnapshot document = task.getResult();
                             if (document.exists()) {
                                 Map<String, Object> data = document.getData();
                                 ArrayList<Map<String, Object>> healthList = (ArrayList<Map<String, Object>>) data.get("health_policy");
                                 try{
                                     if (healthList != null){
                                         int position = getIntent().getIntExtra("position", -1);
                                         Map<String, Object> healthData = healthList.get(position);
                                         if (healthData != null) {
                                             policyform.setText((String) healthData.get("policyfrom"));
                                             policytype.setText((String) healthData.get("policytype"));
                                             policynumber.setText((String) healthData.get("policynumber"));
                                             firstpolicynumber.setText((String) healthData.get("firstpolicynumber"));
                                             gst.setText((String) healthData.get("gst"));
                                             suma.setText((String) healthData.get("suma"));
                                             ttd.setText((String) healthData.get("ttd"));
                                             firstpolicydate.setText((String) healthData.get("fistpolicydate"));
                                             policystartdate.setText((String) healthData.get("policystartdate"));
                                             policyenddate.setText((String) healthData.get("policyenddate"));
                                             payurl=(String) healthData.get("paylink");
                                             receipt=(String) healthData.get("receipt_url");

                                         }
                                     }else{
                                         Toast.makeText(Policy_Form_Activity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                     }
                                 }catch (Exception e){
                                     Toast.makeText(Policy_Form_Activity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                 }


                             }
                         }
                     }
                 }
                );
            }catch (Exception e){
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else {
            try{
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
                             ArrayList<Object> healthPolicyList = (ArrayList<Object>) childData.get("health_policy");
                             if (healthPolicyList != null){
                                 int position = getIntent().getIntExtra("position", -1);
                                 Map<String, Object> healthData = (Map<String, Object>) healthPolicyList.get(position);
                                 if (healthData != null) {
                                     policyform.setText((String) healthData.get("policyfrom"));
                                     policytype.setText((String) healthData.get("policytype"));
                                     policynumber.setText((String) healthData.get("policynumber"));
                                     firstpolicynumber.setText((String) healthData.get("firstpolicynumber"));
                                     gst.setText((String) healthData.get("gst"));
                                     suma.setText((String) healthData.get("suma"));
                                     ttd.setText((String) healthData.get("ttd"));
                                     firstpolicydate.setText((String) healthData.get("fistpolicydate"));
                                     policystartdate.setText((String) healthData.get("policystartdate"));
                                     policyenddate.setText((String) healthData.get("policyenddate"));
                                     payurl=(String) healthData.get("paylink");
                                     receipt=(String) healthData.get("receipt_url");


                                 }
                             }else{
                                 Toast.makeText(Policy_Form_Activity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                             }
                         }


                     }
                 }
             }
         }
                );
            }catch (Exception e){
                Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        }



        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileDownloader.downloadFile(receipt,getApplicationContext());
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent url= new Intent();
                    url.setAction(Intent.ACTION_VIEW);
                    url.addCategory(Intent.CATEGORY_BROWSABLE);
                    url.setData(Uri.parse(payurl));
                    startActivity(url);
                }catch (Exception e){
                    Toast.makeText(Policy_Form_Activity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });
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
