package com.Lohia.investment.Adapter;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Models.user_data_model;
import com.Lohia.investment.Policy_Form_Activity;
import com.Lohia.investment.Update_Request_Activity;
import com.bumptech.glide.Glide;
import com.Lohia.investment.R;

import java.util.ArrayList;

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.user_data> {
    ArrayList<user_data_model> userArrayList;
    Context context;

    public UserDataAdapter(ArrayList<user_data_model> userArrayList, Context context) {
        this.userArrayList = userArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserDataAdapter.user_data onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_data_layout, parent, false);
        return new user_data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull user_data holder, @SuppressLint("RecyclerView") int position) {
        user_data_model userDataModel= userArrayList.get(position);
        holder.type.setText((String) userDataModel.getPolicytype());
        holder.time.setText((String) userDataModel.getPolicystartdate());
        holder.gst.setText((String) userDataModel.getGst());
        holder.suma.setText((String) userDataModel.getSuma());
        String payurl = userDataModel.getPaylink();
        String logo_url = userDataModel.getLogo();
        int rootcheck = userDataModel.getRootcheck();
        try{
            Glide.with(context).load(logo_url).into(holder.logo);
        }catch (Exception e){
            holder.logo.setImageResource(R.drawable.dlogo);
        }

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Update_Request_Activity.class);
                intent.putExtra("To_CHECK", "health");
                intent.putExtra("POLICY_NUMBER", userDataModel.getPolicystartdate());
                intent.putExtra("GST", userDataModel.getGst());
                intent.putExtra("Position", position);
                intent.putExtra("User_index", rootcheck);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent url= new Intent();
                url.setAction(Intent.ACTION_VIEW);
                url.addCategory(Intent.CATEGORY_BROWSABLE);
                url.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                url.setData(Uri.parse(payurl));
                context.startActivity(url);
            }
        });
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Policy_Form_Activity.class);
                intent.putExtra("position", position);
                intent.putExtra("rootcheck",rootcheck);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public void setData(ArrayList<user_data_model> userArrayList) {
        this.userArrayList = userArrayList;
        notifyDataSetChanged();
    }

    public class user_data extends RecyclerView.ViewHolder {
        TextView type,time,gst,suma;
        ImageView logo;
        Button pay,show,update;
        public user_data(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.userdata_text_field_one);
            time=itemView.findViewById(R.id.userdata_text_field_two);
            gst=itemView.findViewById(R.id.userdata_text_field_three);
            suma=itemView.findViewById(R.id.userdata_text_field_four);
            logo=itemView.findViewById(R.id.Com_logo);
            pay=itemView.findViewById(R.id.ud_submit);
            show=itemView.findViewById(R.id.ud_show);
            update=itemView.findViewById(R.id.ud_update);
        }
    }

}
