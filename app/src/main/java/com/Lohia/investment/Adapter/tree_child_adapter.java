package com.Lohia.investment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Lohia.investment.Models.Tree_Child_Model;
import com.Lohia.investment.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class tree_child_adapter extends RecyclerView.Adapter<tree_child_adapter.tree_child_view> {


    private final tree_adapter_interface tree_interface;
    ArrayList<Tree_Child_Model> treeChildModels;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Context context;
    int loop ;
    private ArrayList<Tree_Child_Model> treeChildModelsList;

    public tree_child_adapter(tree_adapter_interface tree_interface, ArrayList<Tree_Child_Model> treeChildModels) {
        this.tree_interface = tree_interface;
        this.treeChildModels = treeChildModels;
    }


    @NonNull
    @Override
    public tree_child_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_tree_show, parent, false);
        return new tree_child_view(view);
    }

    public void setData(ArrayList<Tree_Child_Model> data) {
        this.treeChildModelsList = data;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
    @Override
    public void onBindViewHolder(@NonNull tree_child_view holder, int position) {
             Tree_Child_Model data=treeChildModels.get(position);
                            if(data != null){
                                holder.textchlid.setVisibility(View.VISIBLE);
                                holder.imagechild.setVisibility(View.VISIBLE);
                                holder.textchlid.setText((String) data.getC_name());
                                String gender= (String) data.getC_gender();
                                if(gender.equals("female")){
                                    holder.imagechild.setImageResource(R.drawable.girl);
                                }else {
                                    holder.imagechild.setImageResource(R.drawable.boy);
                                }
                        }else {
                                holder.textchlid.setVisibility(View.GONE);
                                holder.imagechild.setVisibility(View.GONE);;
                            }
                        }


    @Override
    public int getItemCount() {
        // Return the number of items in your data source
        // For example: return dataList.size();
        return treeChildModels.size(); // Update this to the actual item count
    }

    public class tree_child_view extends RecyclerView.ViewHolder {
        TextView textchlid;
        ImageView imagechild;
        public tree_child_view(@NonNull View itemView) {
            super(itemView);
            textchlid=itemView.findViewById(R.id.idTreeView_child);
            imagechild=itemView.findViewById(R.id.child_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && tree_interface != null) {
                        int ch=0;
                        tree_interface.onItemClick(pos, ch);
                    }
                }
            });
        }
    }
}
