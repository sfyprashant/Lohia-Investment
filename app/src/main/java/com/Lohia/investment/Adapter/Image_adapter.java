package com.Lohia.investment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.Lohia.investment.R;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;


import java.util.List;

public class Image_adapter extends RecyclerView.Adapter<Image_adapter.SliderViewHolder> {

    private List<String> imagesList;
    Context context;

    public Image_adapter(Context context, List<String> imagesList) {
        this.context = context;
        this.imagesList = imagesList;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        // Show a placeholder while loading
        Glide.with(context)
                .load(imagesList.get(position))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.i1) // Placeholder image
                        .error(R.drawable.i2) // Error image if loading fails
                )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.imageView);

        // Show loading indicator while the image is being loaded
        holder.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;

        SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_image);
            progressBar = itemView.findViewById(R.id.progressBar);

            // Hide the loading indicator initially
            progressBar.setVisibility(View.GONE);
        }
    }
}
