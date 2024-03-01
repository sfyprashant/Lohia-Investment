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

import com.bumptech.glide.Glide;
import com.Lohia.investment.Models.Term_Data_Model;
import com.Lohia.investment.R;
import com.Lohia.investment.TermPolicyFromActivity;
import com.Lohia.investment.Update_Request_Activity;

import java.util.ArrayList;

public class TermDataAdapter extends RecyclerView.Adapter<TermDataAdapter.term_data> {
    ArrayList<Term_Data_Model> termArrayList;
    Context context;

    public TermDataAdapter(ArrayList<Term_Data_Model> termArrayList, Context context) {
        this.termArrayList = termArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TermDataAdapter.term_data onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.term_layout, parent, false);
        return new term_data(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermDataAdapter.term_data holder, @SuppressLint("RecyclerView") int position) {
        Term_Data_Model termDataModel= termArrayList.get(position);
        holder.policytype.setText((String) termDataModel.getPolicytype());
        holder.time.setText((String) termDataModel.getDateofcommencement());
        holder.gst.setText((String) termDataModel.getPremium());
        holder.suma.setText((String) termDataModel.getSum());
        String payurl = termDataModel.getPaylink();
        String logo_url = termDataModel.getLogo();
        int rootcheck = termDataModel.getRootcheck();
        try{
            Glide.with(context).load(logo_url).into(holder.logo);
        }catch (Exception e){
            holder.logo.setImageResource(R.drawable.dlogo);
        }
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, Update_Request_Activity.class);
                intent.putExtra("To_CHECK", "term");
                intent.putExtra("POLICY_NUMBER", termDataModel.getDateofcommencement());
                intent.putExtra("GST", termDataModel.getPremium());
                intent.putExtra("Position", position);
                intent.putExtra("User_index", rootcheck);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, TermPolicyFromActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("rootcheck",rootcheck);
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
    }

    @Override
    public int getItemCount() {
        return termArrayList.size();
    }

    public void setData(ArrayList<Term_Data_Model> termArrayList) {
        this.termArrayList = termArrayList;
        notifyDataSetChanged();
    }

    public class term_data extends RecyclerView.ViewHolder{
        TextView policytype,time,gst,suma;
        Button show,pay,update;
        ImageView logo;
        public term_data(@NonNull View itemView) {
            super(itemView);
            time=itemView.findViewById(R.id.term_data_text_field_two);
            policytype=itemView.findViewById(R.id.term_data_text_field_one);
            gst=itemView.findViewById(R.id.term_data_text_field_three);
            suma=itemView.findViewById(R.id.term_data_text_field_four);
            logo=itemView.findViewById(R.id.Com_logo);
            show=itemView.findViewById(R.id.ud_show);
            pay=itemView.findViewById(R.id.ud_submit);
            update=itemView.findViewById(R.id.ud_update);
        }
    }
}
