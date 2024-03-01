package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
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

import com.Lohia.investment.Adapter.UserDataAdapter;
import com.Lohia.investment.Models.user_data_model;
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

public class UserDataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView back;
    RecyclerView userdatarecyerview;
    ArrayList<user_data_model> userArrayList = new ArrayList<>();
    Button show,pay;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    UserDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        show=findViewById(R.id.ud_show);
        pay=findViewById(R.id.ud_submit);
        back = findViewById(R.id.backbtn);
        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });


        SharedPreferences pre= getSharedPreferences("login",MODE_PRIVATE);
        String num=pre.getString("num","data no get yet");
        CollectionReference usersCollection = db.collection("users");
        DocumentReference userDocument = usersCollection.document(num);
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document=task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        ArrayList<Map<String, Object>> healthList = (ArrayList<Map<String, Object>>) data.get("health_policy");
                        try {
                            if(healthList != null){
                                for (Map<String, Object> health : healthList) {
                                    if (health != null) {
                                        String type = ((String) health.get("policytype"));
                                        String time = ((String) health.get("policynumber"));
                                        String gst = ((String) health.get("gst"));
                                        String suma = ((String) health.get("suma"));
                                        String payurl = (String) health.get("paylink");
                                        String logo_url = (String) health.get("logo_url");
                                        int rootcheck = -1;
                                        user_data_model userDataModel = new user_data_model(suma, gst, type, time, payurl,logo_url,rootcheck);
                                        userArrayList.add(userDataModel);
                                        adapter = new UserDataAdapter(userArrayList, getApplicationContext());
                                        userdatarecyerview.setAdapter(adapter);

                                    }else{
                                        Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                        int drawableResourceId = R.drawable.health;
                                        intent.putExtra("IMG",drawableResourceId);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }else {
                                Toast.makeText(UserDataActivity.this, "You Don't have Health Policy", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                int drawableResourceId = R.drawable.health;
                                intent.putExtra("IMG",drawableResourceId);
                                startActivity(intent);
                                finish();
                            }
                        }catch (Exception e) {
                            Toast.makeText(UserDataActivity.this, "You Don't have Health Policy", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                            int drawableResourceId = R.drawable.health;
                            intent.putExtra("IMG",drawableResourceId);
                            startActivity(intent);
                            finish();
                        }
                        updateUIWithChildData(userArrayList);
                    }else{
                        Toast.makeText(UserDataActivity.this, "Task Data", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(UserDataActivity.this, "Task Data", Toast.LENGTH_SHORT).show();
                }
            }});


        userdatarecyerview = findViewById(R.id.user_data_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        userdatarecyerview.setLayoutManager(layoutManager);
        drawerLayout = findViewById(R.id.drawerLayout2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void updateUIWithChildData(ArrayList<user_data_model> userArrayList) {
        if (adapter != null) {
            adapter.setData(userArrayList);
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
