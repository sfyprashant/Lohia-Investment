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

public class TermPolicyFromActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    TextView title,policyform,policytype,compenyname,type,policynumber,mode,firstunpaidpremium,gst,suma,planename,term,ppterm,dateofcommencement,premiumpaymentupto,approxmaturityamount,maturitydate,paymentmode,channel;
    Button pay,download;
    ImageView back;
    String payurl,receipt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_policy_from);
        download=findViewById(R.id.term_download);
        policyform=findViewById(R.id.term_policy_form_title_one);
        policytype=findViewById(R.id.term_policy_form_title_two);
        compenyname=findViewById(R.id.term_policy_form_title_three);
        type=findViewById(R.id.term_policy_form_title_four);
        policynumber=findViewById(R.id.term_policy_form_title_five);
        mode=findViewById(R.id.term_policy_form_title_six);
        firstunpaidpremium=findViewById(R.id.term_policy_form_title_seven);
        gst=findViewById(R.id.term_policy_form_title_eight);
        suma=findViewById(R.id.term_policy_form_title_nine);
        planename=findViewById(R.id.term_policy_form_title_ten);
        term=findViewById(R.id.term_policy_form_title_eleven);
        ppterm=findViewById(R.id.term_policy_form_title_twe);
        dateofcommencement=findViewById(R.id.term_policy_form_title_thirteen);
        premiumpaymentupto=findViewById(R.id.term_policy_form_title_forteen);
        approxmaturityamount=findViewById(R.id.term_policy_form_title_fifteen);
        maturitydate=findViewById(R.id.term_policy_form_title_sixteen);
        paymentmode=findViewById(R.id.term_policy_form_title_seventeen);

        back=findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        pay=findViewById(R.id.policy_form_pay_now);


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
                                     ArrayList<Map<String, Object>> termList = (ArrayList<Map<String, Object>>) data.get("term_policy");
                                     int position = getIntent().getIntExtra("position", -1);
                                     Map<String, Object> termData = termList.get(position);
                                     if (termData != null) {
                                         policyform.setText((String) termData.get("policyfrom"));
                                         compenyname.setText((String) termData.get("companyname"));
                                         policytype.setText((String) termData.get("types"));
                                         type.setText((String) termData.get("types"));
                                         policynumber.setText((String) termData.get("policynumber"));
                                         mode.setText((String) termData.get("mode"));
                                         firstunpaidpremium.setText((String) termData.get("firstunpaidpremium"));
                                         gst.setText((String) termData.get("premiumgst"));
                                         suma.setText((String) termData.get("sumassured"));
                                         planename.setText((String) termData.get("planname"));
                                         term.setText((String) termData.get("term"));
                                         ppterm.setText((String) termData.get("premiumpayingterm"));
                                         dateofcommencement.setText((String) termData.get("dateofcommencement"));
                                         premiumpaymentupto.setText((String) termData.get("premiumpaymentupto"));
                                         approxmaturityamount.setText((String) termData.get("approxmaturityamount"));
                                         maturitydate.setText((String) termData.get("maturitydate"));
                                         paymentmode.setText((String) termData.get("paymentmode"));
                                         payurl=(String) termData.get("paylink");
                                         receipt=(String) termData.get("receipt_url");

                                     }
                                 }
                             }
                         }
                     }
                );


            }catch (Exception e){
                Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
        }else{
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
                                 ArrayList<Map<String, Object>> termList = (ArrayList<Map<String, Object>>) childData.get("term_policy");
                                 int position = getIntent().getIntExtra("position", -1);
                                 Map<String, Object> termData = termList.get(position);
                                 if (termData != null) {
                                     policyform.setText((String) termData.get("policyfrom"));
                                     compenyname.setText((String) termData.get("companyname"));
                                     policytype.setText((String) termData.get("types"));
                                     type.setText((String) termData.get("types"));
                                     policynumber.setText((String) termData.get("policynumber"));
                                     mode.setText((String) termData.get("mode"));
                                     firstunpaidpremium.setText((String) termData.get("firstunpaidpremium"));
                                     gst.setText((String) termData.get("premiumgst"));
                                     suma.setText((String) termData.get("sumassured"));
                                     planename.setText((String) termData.get("planname"));
                                     term.setText((String) termData.get("term"));
                                     ppterm.setText((String) termData.get("premiumpayingterm"));
                                     dateofcommencement.setText((String) termData.get("dateofcommencement"));
                                     premiumpaymentupto.setText((String) termData.get("premiumpaymentupto"));
                                     approxmaturityamount.setText((String) termData.get("approxmaturityamount"));
                                     maturitydate.setText((String) termData.get("maturitydate"));
                                     paymentmode.setText((String) termData.get("paymentmode"));
                                     payurl=(String) termData.get("paylink");
                                     receipt=(String) termData.get("receipt_url");

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