package com.Lohia.investment.ChildTree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.Lohia.investment.Adapter.CarDataAdapter;
import com.Lohia.investment.MainActivity;
import com.Lohia.investment.Models.Car_Data_Model;
import com.Lohia.investment.No_Policy_Page;
import com.Lohia.investment.R;
import com.Lohia.investment.signupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Child_Car_Data extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    int CHILD_INDEX;
    Intent intent;


    DrawerLayout drawerLayout;
    Toolbar toolbar;
    RecyclerView cardatarecyerview;
    ArrayList<Car_Data_Model> carArrayList = new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CarDataAdapter adapter;
    ImageView back,slogo;
    String payurl,logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_car_data);
        intent = getIntent();
        if(intent != null) {
            CHILD_INDEX = intent.getIntExtra("child_index", 0);
        }

        back = findViewById(R.id.backbtn);
        try {
            back.setOnClickListener(view -> {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            });
        }catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }

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
                            ArrayList<Map<String, Object>> childList = (ArrayList<Map<String, Object>>) data.get("children");
                            if (childList != null && childList.size() > 0) {
                                Map<String, Object> childData = childList.get(CHILD_INDEX);
                                ArrayList<Map<String, Object>> carList = (ArrayList<Map<String, Object>>) childData.get("car_policy");
                                try {
                                    if (carList != null){
                                        for (Map<String, Object> car : carList) {
                                            if (car != null) {
                                                String policytype= (String)car.get("policytype");
                                                String Premium= (String)car.get("premium");
                                                String time= (String)car.get("policynumber");
                                                String companyname= (String)car.get("companyname");
                                                String payurl = (String) car.get("paylink");
                                                String logo_url = (String) car.get("logo_url");
                                                int rootcheck = CHILD_INDEX;
                                                Car_Data_Model carDataModel = new Car_Data_Model(policytype,time,companyname,Premium,payurl,logo_url,rootcheck);
                                                carArrayList.add(carDataModel);
                                                adapter = new CarDataAdapter(carArrayList,getApplicationContext());
                                                cardatarecyerview.setAdapter(adapter);
                                            }else{
                                                Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                                int drawableResourceId = R.drawable.motor;
                                                intent.putExtra("IMG",drawableResourceId);
                                                startActivity(intent);
                                                finish();
                                                Toast.makeText(Child_Car_Data.this, "You Don't have Motor Policy", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }else {
                                        Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                        int drawableResourceId = R.drawable.motor;
                                        intent.putExtra("IMG",drawableResourceId);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(Child_Car_Data.this, "You Don't have Motor Policy", Toast.LENGTH_SHORT).show();
                                    }
                                }catch (Exception e){
                                    Intent intent = new Intent(getApplicationContext(),No_Policy_Page.class);
                                    int drawableResourceId = R.drawable.motor;
                                    intent.putExtra("IMG",drawableResourceId);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "You Don't have Motor Policy", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                    }
                }
            });
//            yaha iss array me add karna hai jese yaha  static data diya hai vese hi add karna hai
//            carArrayList.add(new Car_Data_Model("policytype","908070","lohia","101010","https:google.com"));
        }
        catch (Exception e){
            Toast.makeText(this, "Please Try Later", Toast.LENGTH_SHORT).show();
        }

        cardatarecyerview = findViewById(R.id.car_data_recyclerview);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        cardatarecyerview.setLayoutManager(layoutManager3);
        
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
            startActivity(new Intent(getApplicationContext(), signupActivity.class));
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