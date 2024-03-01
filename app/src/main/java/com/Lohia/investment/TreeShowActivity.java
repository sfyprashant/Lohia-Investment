package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Adapter.tree_child_adapter;
import com.Lohia.investment.ChildTree.Child_Car_Data;
import com.Lohia.investment.ChildTree.Child_Health_Data;
import com.Lohia.investment.ChildTree.Child_Terms_Data;
import com.Lohia.investment.Adapter.tree_adapter;
import com.Lohia.investment.Adapter.tree_adapter_interface;
import com.Lohia.investment.Models.All_Data;
import com.Lohia.investment.Models.Tree_Child_Model;
import com.Lohia.investment.Models.Tree_Model;
import com.Lohia.investment.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Map;

public class TreeShowActivity extends AppCompatActivity implements tree_adapter_interface, NavigationView.OnNavigationItemSelectedListener {

    // Add this in your Java class

    DrawerLayout drawerLayout;
    tree_child_adapter adapter1;
    Toolbar toolbar;
    RecyclerView treerecyclerView,childtreerecyclerview;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    All_Data userData = new All_Data();
    private final ArrayList<Tree_Model> treeModelsList = new ArrayList<>();
    private ArrayList<Tree_Child_Model> treeChildModelsList = new ArrayList<>();

    ImageView back;
    String collation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_show);
        userData.children = new ArrayList<>();
        userData.health_policy = new ArrayList<>();
        userData.car_policy = new ArrayList<>();
        userData.term_policy = new ArrayList<>();



        Intent intent = this.getIntent();
        back = findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

//        navigation drawer
        drawerLayout = findViewById(R.id.drawerLayout2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);

//
//        progressDialog=new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("fetching.....");
//        progressDialog.show();
        try {
            CollectionReference usersCollection = db.collection("users");
            SharedPreferences pre= getSharedPreferences("login", MODE_PRIVATE);
            String num=pre.getString("num","No Data");
            DocumentReference userDocument = usersCollection.document(num);
            userDocument.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        // Handle any errors here.
                        Log.e("FirestoreError", "Error fetching data: " + e.getMessage());
                        return;
                    }

                    if (document != null && document.exists()) {
                        Map<String, Object> data = document.getData();
                        ArrayList<Map<String, Object>> childList = (ArrayList<Map<String, Object>>) data.get("children");
                        treeChildModelsList.clear(); // Clear the list to avoid duplicates if data changes
                        if(childList != null){
                            for (Map<String, Object> childData : childList) {
                                if (childData != null) {
                                    String name = (String) childData.get("name");
                                    String gander = (String) childData.get("gander");
                                    Tree_Child_Model tree_child_model = new Tree_Child_Model(gander, name);
                                    treeChildModelsList.add(tree_child_model);
                                    adapter1 =new tree_child_adapter(TreeShowActivity.this,treeChildModelsList);
                                    childtreerecyclerview.setAdapter(adapter1);
                                }
                            }
                            updateUIWithChildData(treeChildModelsList);
                        }else{

                        }

                    } else {

                    }

                }
            });
            collation = getIntent().getStringExtra("check");
            All_Data userData = getIntent().getParcelableExtra("userData");
            if (userData != null) {
                String name = userData.name;
                String gender = userData.gander;
                Tree_Model tree_model = new Tree_Model(gender, name);
                treeModelsList.add(tree_model);
            } else {
                Toast.makeText(this, "UserData is null", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            collation = getIntent().getStringExtra("check");
            All_Data userData = getIntent().getParcelableExtra("userData");
            if (userData != null) {
                String name = userData.name;
                String gender = userData.gander;
                Tree_Model tree_model = new Tree_Model(gender, name);
                treeModelsList.add(tree_model);
            } else {
                Toast.makeText(this, "UserData is null", Toast.LENGTH_SHORT).show();
            }
        }




        treerecyclerView = findViewById(R.id.idTreeView);
        treerecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        treerecyclerView.setLayoutManager(layoutManager);
        tree_adapter adapter = new tree_adapter(TreeShowActivity.this, treeModelsList);
        treerecyclerView.setAdapter(adapter);
        childtreerecyclerview = findViewById(R.id.idTreeView_child);
        childtreerecyclerview.setHasFixedSize(true);
        LinearLayoutManager childLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        childtreerecyclerview.setLayoutManager(childLayoutManager);


    }



    @Override
    public void onItemClick(int position, int ch) {
        if(ch == 1){
            if (collation.equals("health_policy")){
                Intent intent=new Intent(getApplicationContext(), UserDataActivity.class);
                startActivity(intent);
            }else if (collation.equals("car_policy")){
                Intent intent=new Intent(getApplicationContext(), CarDataActivity.class);
                startActivity(intent);
            }else if(collation.equals("term_policy")){
                Intent intent=new Intent(getApplicationContext(), TermDataActivity.class);
                startActivity(intent);
            }else{
                Intent intent=new Intent(getApplicationContext(), HomeDataActivity.class);
                startActivity(intent);
            }
        }else if(ch == 0){
            if (collation.equals("health_policy")) {
                Intent intent = new Intent(getApplicationContext(), Child_Health_Data.class);
                intent.putExtra("child_index", position);
                startActivity(intent);
            }else if (collation.equals("car_policy")){
                Intent intent=new Intent(getApplicationContext(), Child_Car_Data.class);
                intent.putExtra("child_index", position);
                startActivity(intent);
            }else if(collation.equals("term_policy")){
                Intent intent=new Intent(getApplicationContext(), Child_Terms_Data.class);
                intent.putExtra("child_index", position);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
    void updateUIWithChildData(ArrayList<Tree_Child_Model> data) {
        // Here, you can use treeChildModelsList to update your UI
        // For example, if you have a RecyclerView, you can update its adapter
        // or if you want to display the data in a TextView, you can set the text here
        // Example for a RecyclerView:
        if (adapter1 != null) {
            adapter1.setData(data);
            adapter1.notifyDataSetChanged();
        }
    }

}