package com.Lohia.investment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Models.FD_Model;
import com.Lohia.investment.R;

import java.util.ArrayList;

public class FD_Adapter extends RecyclerView.Adapter<FD_Adapter.FD_DATA>{
    ArrayList<FD_Model> fdArrayList;
    Context context;

    public FD_Adapter(ArrayList<FD_Model> fdArrayList, Context context) {
        this.fdArrayList = fdArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public FD_DATA onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.fd_layout, parent, false);
        return new FD_DATA(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FD_DATA holder, int position) {
        int a = 1+position;
        FD_Model FModel= fdArrayList.get(position);
        holder.title_text.setText("Fixed Deposits "+a);
        holder.fdFrom.setText(FModel.getFD_from());
        holder.fdType.setText(FModel.getFD_type());
        holder.OrganizationBank.setText(FModel.getOrganizationBank());
        holder.OrganizationPB.setText(FModel.getOrganizationPB());
        holder.Frequency_interest.setText(FModel.getFrequency_interest());
        holder.FDRNo.setText(FModel.getFDRNo());
        holder.Interest_Rate.setText(FModel.getInterest_Rate());
        holder.Maturity_Date.setText(FModel.getMaturity_Date());
        holder.Nominee_Name.setText(FModel.getNominee_Name());
    }

    @Override
    public int getItemCount() {
        if (!fdArrayList.isEmpty()){
            return fdArrayList.size();
        }else{
            Toast.makeText(context, "NO SIZE", Toast.LENGTH_SHORT).show();
            return 1;
        }

    }

    public class FD_DATA extends RecyclerView.ViewHolder{
        TextView title_text,fdFrom,fdType,OrganizationBank,OrganizationPB,Frequency_interest,FDRNo,Interest_Rate,Investment_Amount,Maturity_Date,Nominee_Name;
        public FD_DATA(@NonNull View itemView) {
            super(itemView);
            title_text=itemView.findViewById(R.id.title_text);
            fdFrom=itemView.findViewById(R.id.fd_one);
            fdType=itemView.findViewById(R.id.fd_two);
            OrganizationBank=itemView.findViewById(R.id.fd_three);
            OrganizationPB=itemView.findViewById(R.id.fd_four);
            Frequency_interest=itemView.findViewById(R.id.fd_five);
            FDRNo=itemView.findViewById(R.id.fd_six);
            Interest_Rate=itemView.findViewById(R.id.fd_seven);
            Investment_Amount=itemView.findViewById(R.id.fd_eight);
            Maturity_Date=itemView.findViewById(R.id.fd_nine);
            Nominee_Name=itemView.findViewById(R.id.fd_ten);
        }
    }


}
