package com.Lohia.investment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.Lohia.investment.R;


import java.util.List;

public class UploadImageAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final List<String> imagesList;
    private final List<String> check;

    public UploadImageAdapter(Context context, List<String> imagesList, List<String> check) {
        super(context, R.layout.list_view_uploaded, imagesList);
        this.context = context;
        this.imagesList = imagesList;
        this.check = check;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_view_uploaded, parent, false);
        String checkValue = check.get(position);
        ImageView imageView = rowView.findViewById(R.id.imageView);
        Glide.with(context).load(imagesList.get(position)).into(imageView);
        TextView background_layer = rowView.findViewById(R.id.back_ground_layer);
        if (!Boolean.parseBoolean(checkValue)) {
            background_layer.setBackgroundResource(R.drawable.red_border); // Set to a red border resource
            background_layer.setText(R.string.reject);
        } else if (Boolean.parseBoolean(checkValue)) {
            background_layer.setBackgroundResource(R.drawable.green_border); // Set to a green border resource
            background_layer.setText(R.string.accepted);
        } else {
            background_layer.setBackgroundResource(R.drawable.transparent_border);
            background_layer.setText(R.string.pending);
        }

        return rowView;
    }
}