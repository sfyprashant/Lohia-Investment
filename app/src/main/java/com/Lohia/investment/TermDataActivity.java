package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Adapter.TermDataAdapter;
import com.Lohia.investment.Models.Term_Data_Model;
import com.Lohia.investment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class TermDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    ImageView back,slogo;
    String payurl;
    TermDataAdapter adapter;
    RecyclerView termdatarecyerview;
    ArrayList<Term_Data_Model> termArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_data);
        back=findViewById(R.id.backbtn);
        slogo=findViewById(R.id.Com_logo);
        back.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),MainActivity.class)));


        try {
            SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
            String num = pre.getString("num", "data no get yet");
            CollectionReference usersCollection = db.collection("users");
            DocumentReference userDocument = usersCollection.document(num);
            userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Map<String, Object> data = document.getData();
                            ArrayList<Map<String, Object>> termList = (ArrayList<Map<String, Object>>) data.get("term_policy");
                            try {
                                if (termList != null){
                                    for (Map<String, Object> term : termList) {
                                        if (term != null) {
                                            String suma=(String) term.get("sumassured");
                                            String policytype=(String) term.get("types");
                                            String gst=(String) term.get("premiumgst");
                                            String time=(String) term.get("policynumber");
                                            String logo_url = (String) term.get("logo_url");
                                            payurl = (String) term.get("paylink");
                                            int rootcheck = -1;
                                            Term_Data_Model termDataModel = new Term_Data_Model(policytype,time,gst,suma,payurl,logo_url,rootcheck);
                                            termArrayList.add(termDataModel);
                                            adapter = new TermDataAdapter(termArrayList,getApplicationContext());
                                            termdatarecyerview.setAdapter(adapter);
                                        }else{
                                            Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                            int drawableResourceId = R.drawable.life;
                                            intent.putExtra("IMG",drawableResourceId);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                    updateUIWithChildData(termArrayList);
                                }else {
                                    Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                    int drawableResourceId = R.drawable.life;
                                    intent.putExtra("IMG",drawableResourceId);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(TermDataActivity.this, "You Don't have Life Policy", Toast.LENGTH_SHORT).show();
                                }
                            }catch (Exception e){
                                Toast.makeText(TermDataActivity.this, "You Don't have Life Policy", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
            });

        }
        catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }
        termdatarecyerview = findViewById(R.id.term_data_recyclerview);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        termdatarecyerview.setLayoutManager(layoutManager3);



        drawerLayout = findViewById(R.id.drawerLayout2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void updateUIWithChildData(ArrayList<Term_Data_Model> termArrayList) {
        if (adapter != null) {
            adapter.setData(termArrayList);
            adapter.notifyDataSetChanged();
        }
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