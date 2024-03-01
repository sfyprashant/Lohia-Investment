package com.Lohia.investment.Adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Models.Tree_Model;
import com.Lohia.investment.R;

import java.util.ArrayList;

public class tree_adapter extends RecyclerView.Adapter<tree_adapter.tree_view> {

    private final tree_adapter_interface tree_interface;

    Context context;
   ArrayList<Tree_Model> treeModelsList;
   Intent intent;


    public tree_adapter(tree_adapter_interface tree_interface, ArrayList<Tree_Model> treeModelsList) {
        this.tree_interface = tree_interface;
        this.treeModelsList = treeModelsList;
    }

    @NonNull
    @Override
    public tree_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tree_view_node, parent, false);
        return new tree_view(view);

    }

    @Override
    public void onBindViewHolder(@NonNull tree_view holder, int position) {
        if(treeModelsList!=null){
            Tree_Model treeModel =treeModelsList.get(position);
            holder.treetext.setText(treeModel.getName());
            String gender=treeModel.getGender();
            if(gender.equals("female")){
                holder.imageView.setImageResource(R.drawable.female);
            }else {
                holder.imageView.setImageResource(R.drawable.male);
            }
        }

    }

    @Override
    public int getItemCount() {
      return treeModelsList.size();
    }

    public class tree_view extends RecyclerView.ViewHolder {
        TextView treetext;
        ImageView imageView;
        public tree_view(@NonNull View itemView) {
            super(itemView);
            treetext=itemView.findViewById(R.id.idTreeView);
            imageView=itemView.findViewById(R.id.idTreeimage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tree_interface != null){
                        int pos=getAdapterPosition();
                        int ch = 1;
                        if(pos != RecyclerView.NO_POSITION){
                            tree_interface.onItemClick(pos,ch);
                        }
                    }
                }
            });
        }

    }
}
