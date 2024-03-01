package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Lohia.investment.Adapter.ADD_USER_POLICY_ADAPTER;
import com.Lohia.investment.Models.ADD_POLICY_USER_MODEL;
import com.Lohia.investment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Add_Policy_Fragment extends Fragment {


    RecyclerView CARD;
    String num,userName,Dob,Number;
    ArrayList<ADD_POLICY_USER_MODEL> ADD_POLICY_USER = new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    TextView Titlename,Titledob,Titlenumber;
    Button Add_policy;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add__policy_, container, false);
        Titlename =  view.findViewById(R.id.title_name);
        Titledob =  view.findViewById(R.id.title_dob);
        Titlenumber =  view.findViewById(R.id.title_number);
        Add_policy =  view.findViewById(R.id.Add_policy_user);
        SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
        num = pre.getString("num", "data no get yet");
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..."); // Set your message here
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {
            CollectionReference usersCollection = db.collection("users");
            DocumentReference userDocument = usersCollection.document(num);
            userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DocumentSnapshot document = task.getResult();
                         if (document.exists()) {
                             Map<String, Object> data = document.getData();
                             userName = data.get("name").toString();
                             Dob = data.get("dob").toString();
                             Number = data.get("number").toString();
                             Titlename.setText(userName);
                             Titledob.setText(Dob);
                             Titlenumber.setText(Number);
                             String childName,childDob,childNumber;
                             List<Map<String, Object>> childrenList = (List<Map<String, Object>>) data.get("children");
                             if (childrenList != null) {
                                 for (Map<String, Object> child : childrenList) {
                                     childName = (String) child.get("name");
                                     childDob = child.get("dob").toString();
                                     childNumber = child.get("number").toString();
                                     ADD_POLICY_USER_MODEL ADD_USER = new ADD_POLICY_USER_MODEL(childName,childDob,childNumber);
                                     ADD_POLICY_USER.add(ADD_USER);
                                     ADD_USER_POLICY_ADAPTER adapter = new ADD_USER_POLICY_ADAPTER(ADD_POLICY_USER, getContext()); // Pass your data list
                                     CARD.setAdapter(adapter);
                                     progressDialog.dismiss();
                                 }
                                 CARD.setVisibility(View.VISIBLE);
                             }
                             else {
                                 progressDialog.dismiss();
                                 CARD.setVisibility(View.GONE);
                             }
                         }
                     }
                 }
             }
            );

        }catch (Exception e){
            startActivity(new Intent(getContext(),MainActivity.class));
        }

        Add_policy.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(),Add_Policy_Activity.class);
            intent.putExtra("name",userName);
            intent.putExtra("usertype","parent");
            intent.putExtra("position", 100);
            startActivity(intent);
        });
        CARD= view.findViewById(R.id.child_user_card);
        CARD.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}