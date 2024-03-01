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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Car_showActivity;
import com.Lohia.investment.Models.Car_Data_Model;
import com.Lohia.investment.Update_Request_Activity;
import com.bumptech.glide.Glide;
import com.Lohia.investment.R;


import java.util.ArrayList;

public class CarDataAdapter extends RecyclerView.Adapter<CarDataAdapter.car_data>{
    ArrayList<Car_Data_Model> carArrayList;
    Context context;

    public CarDataAdapter(ArrayList<Car_Data_Model> carArrayList, Context context) {
        this.carArrayList = carArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarDataAdapter.car_data onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.car_layout, parent, false);
        return new car_data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarDataAdapter.car_data holder, @SuppressLint("RecyclerView") int position) {
        Car_Data_Model userDataModel= carArrayList.get(position);
        if (userDataModel != null && userDataModel.getType() != null && userDataModel.getTime() != null && userDataModel.getPremium() != null && userDataModel.getCompanyname() != null && userDataModel.getPaylink() != null) {
            holder.policytype.setText((String) userDataModel.getType());
            holder.time.setText((String) userDataModel.getTime());
            holder.Premium.setText((String) userDataModel.getPremium());
            holder.vehiclecategory.setText((String) userDataModel.getCompanyname());
            String payurl = userDataModel.getPaylink();
            String logo_url = userDataModel.getLogo();
            int rootcheck = userDataModel.getRootcheck();
            try{
                Glide.with(context).load(logo_url).into(holder.logo);
            }catch (Exception e){
                holder.logo.setImageResource(R.drawable.dlogo);
            }
            try {
                holder.update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(context, Update_Request_Activity.class);
                        intent.putExtra("To_CHECK", "car");
                        intent.putExtra("POLICY_NUMBER", userDataModel.getTime());
                        intent.putExtra("GST", userDataModel.getPremium());
                        intent.putExtra("Compney", userDataModel.getCompanyname());
                        intent.putExtra("Position", position);
                        intent.putExtra("User_index", rootcheck);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
            }catch (Exception e){
                Toast.makeText(context, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
            try{
                holder.show.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(context, Car_showActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("rootcheck",rootcheck);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });}catch (Exception e){
                Toast.makeText(context, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
            try{
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
            }catch (Exception e){
                Toast.makeText(context, "Please Try Later", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(context, "Please Try Later", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return carArrayList.size();
    }

    public void setData(ArrayList<Car_Data_Model> carArrayList) {
        this.carArrayList = carArrayList;
        notifyDataSetChanged();
    }

    public class car_data extends RecyclerView.ViewHolder{
        TextView policytype,time,Premium,vehiclecategory;
        Button pay,show,update;
        ImageView logo;
        public car_data(@NonNull View itemView) {
            super(itemView);
            policytype=itemView.findViewById(R.id.cara_data_text_field_one);
            time=itemView.findViewById(R.id.cara_data_text_field_two);
            Premium=itemView.findViewById(R.id.cara_data_text_field_three);
            vehiclecategory=itemView.findViewById(R.id.cara_data_text_field_four);
            logo=itemView.findViewById(R.id.Com_logo);
            show=itemView.findViewById(R.id.ud_show);
            pay=itemView.findViewById(R.id.ud_submit);
            update=itemView.findViewById(R.id.ud_update);
        }
    }

}
