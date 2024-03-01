package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.Lohia.investment.Adapter.FD_Adapter;
import com.Lohia.investment.Models.FD_Model;
import com.Lohia.investment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class FixedDeposits_show_Fragment extends Fragment {

    ArrayList<FD_Model> FDModel = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FD_Adapter adapter;
    RecyclerView recycler_FD;
    LinearLayout FD_LAYOUT;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fixed_deposits_show_, container, false);
        recycler_FD = view.findViewById(R.id.recycler_FD);
        FD_LAYOUT = view.findViewById(R.id.FD_LAYOUT);
        SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
        String num = pre.getString("num", "data no get yet");

        DocumentReference userRef = db.collection("users").document(num);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        ArrayList<Map<String, Object>> fdList = (ArrayList<Map<String, Object>>) data.get("Fixed_Deposits");
                        Log.i("FFAFFAFAFFAFAFFAFFA","gagagaggjajgaggajgj1"+fdList);
                        try {
                            if (fdList != null) {
                                for (Map<String, Object> fd : fdList) {
                                    if (fd != null) {
                                        String fdType = (String) fd.get("FD_from");
                                        String fdAmount = (String) fd.get("FD_type");
                                        String OrganizationBank = (String) fd.get("OrganizationBank");
                                        String OrganizationPB = (String) fd.get("OrganizationPB");
                                        String Frequency_interest = (String) fd.get("Frequency_interest");
                                        String FDRNo = (String) fd.get("FDRNo");
                                        String Interest_Rate = (String) fd.get("Interest_Rate");
                                        String Investment_Amount = (String) fd.get("Investment_Amount");
                                        String Maturity_Date = (String) fd.get("Maturity_Date");
                                        String Nominee_Name = (String) fd.get("Nominee_Name");
                                        FD_Model fdModel = new FD_Model(fdType,fdAmount,OrganizationBank,OrganizationPB,Frequency_interest,FDRNo,Interest_Rate,Investment_Amount,Maturity_Date,Nominee_Name);
                                        FDModel.add(fdModel);
                                        adapter = new FD_Adapter(FDModel,getContext());
                                        recycler_FD.setAdapter(adapter);
                                    } else {
                                        FD_LAYOUT.setVisibility(View.VISIBLE);
                                    }
                                }

                            } else {
                                FD_LAYOUT.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            FD_LAYOUT.setVisibility(View.VISIBLE);

                        }
                    }
                }
            }
        });
        LinearLayoutManager layoutManager9 = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler_FD.setLayoutManager(layoutManager9);
        return view;
    }
}