package com.Lohia.investment.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Add_Policy_Activity;
import com.Lohia.investment.Models.User_profile_model;
import com.Lohia.investment.R;

import java.util.ArrayList;

public class User_profile_adapter extends RecyclerView.Adapter<User_profile_adapter.ViewHolder> {

    private ArrayList<User_profile_model> yourDataList;
    private Context context;


    public User_profile_adapter(Context context, ArrayList<User_profile_model> yourDataList) {
        this.context = context;
        this.yourDataList = yourDataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_profile_custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int po=position;
        User_profile_model currentItem = yourDataList.get(position);
        holder.textViewOne.setText(currentItem.getName());
        holder.healthPolicyNumber.setText(currentItem.getHealth());
        holder.lifePolicyNumber.setText(currentItem.getLife());
        holder.motarPolicyNumber.setText(currentItem.getCar());
        holder.health_cover.setText(currentItem.getHealth_cover());
        holder.life_cover.setText(currentItem.getLife_cover());
        String FORM_URL="https://forms.gle/aHs2FfLZi8vP56Jw8";

        holder.google_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FORM_URL));
                try {
                    context.startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    // Handle the ActivityNotFoundException
                    Toast.makeText(context, "URL Not Working Please Try Latter", Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.addpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Add_Policy_Activity.class);
                intent.putExtra("position", po);
                intent.putExtra("name",currentItem.getName());
                intent.putExtra("usertype","child");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return yourDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOne;
        TextView healthPolicyNumber;
        TextView lifePolicyNumber;
        TextView motarPolicyNumber;
        TextView health_cover;
        TextView life_cover;
        TextView google_form;
        Button addpolicy;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewOne = itemView.findViewById(R.id.textView_one);
            healthPolicyNumber = itemView.findViewById(R.id.health_policy_number);
            lifePolicyNumber = itemView.findViewById(R.id.life_policy_number);
            motarPolicyNumber = itemView.findViewById(R.id.motar_policy_number);
            google_form = itemView.findViewById(R.id.google_form);
            addpolicy = itemView.findViewById(R.id.add_policy_btn);
            health_cover = itemView.findViewById(R.id.health_cover);
            life_cover = itemView.findViewById(R.id.life_cover);
        }
    }
}
