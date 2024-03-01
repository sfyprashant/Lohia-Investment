package com.Lohia.investment.classes;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.Lohia.investment.R;

public class LoaderDialog {

    private Dialog dialog;
    private ProgressBar progressBar;

    public LoaderDialog(Context context) {
        // Create a custom loader dialog
        dialog = new Dialog(context, android.R.style.Theme_Translucent_NoTitleBar);
        View view = LayoutInflater.from(context).inflate(R.layout.loader_dialog_layout, null);
        progressBar = view.findViewById(R.id.progressBar);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }

    // Open the loader dialog
    public void open() {
        if (dialog != null && !dialog.isShowing()) {
            progressBar.setVisibility(View.VISIBLE);
            dialog.show();
        }
    }

    // Close the loader dialog
    public void close() {
        if (dialog != null && dialog.isShowing()) {
            progressBar.setVisibility(View.GONE);
            dialog.dismiss();
        }
    }
}

