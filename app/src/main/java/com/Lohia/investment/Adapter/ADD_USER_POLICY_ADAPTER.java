package com.Lohia.investment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Models.ADD_POLICY_USER_MODEL;
import com.Lohia.investment.Add_Policy_Activity;
import com.Lohia.investment.Models.ADD_POLICY_USER_MODEL;
import com.Lohia.investment.R;

import java.util.ArrayList;

public class ADD_USER_POLICY_ADAPTER extends RecyclerView.Adapter<ADD_USER_POLICY_ADAPTER.USER_CARD>{

    private ArrayList<ADD_POLICY_USER_MODEL> DataModel;
    Context context;



    public ADD_USER_POLICY_ADAPTER(ArrayList<ADD_POLICY_USER_MODEL> addPolicyUser, Context context) {
    }

    @NonNull
    @Override
    public USER_CARD onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_child_card_layout, parent, false);
        return new USER_CARD(view);
    }

    @Override
    public void onBindViewHolder(@NonNull USER_CARD holder, int position) {
        ADD_POLICY_USER_MODEL currentItem = DataModel.get(position);
        int po=position;

        holder.Title.setText(currentItem.getNAME());
        holder.DOB.setText(currentItem.getDOB());
        holder.Number.setText(currentItem.getNUMBER());
        holder.Add_Policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Add_Policy_Activity.class);
                intent.putExtra("position", po);
                intent.putExtra("name",currentItem.getNAME());
                intent.putExtra("usertype","child");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return DataModel.size();
    }

    public class USER_CARD extends RecyclerView.ViewHolder {
        TextView Title,DOB,Number;
        Button Add_Policy;
        public USER_CARD(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title_name);
            DOB = itemView.findViewById(R.id.title_dob);
            Number = itemView.findViewById(R.id.title_number);
            Add_Policy = itemView.findViewById(R.id.Add_policy_child);
        }
    }
}
