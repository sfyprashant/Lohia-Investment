package com.Lohia.investment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lohia.investment.R;

public class mutualFragment extends Fragment {

    LinearLayout client,wealth;
    TextView google_form;
    String clientPackageName = "com.fin.mclientdesk";
    String clientUrl = "https://play.google.com/store/apps/details?id=" + clientPackageName + "&pli=1";
    String FORM_URL = "https://forms.gle/zCJ9kQzG8YAvrJWf9";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_mutual, container, false);

        client=view.findViewById(R.id.client);
        wealth=view.findViewById(R.id.wealth);
        google_form=view.findViewById(R.id.google_form_2);

        google_form.setOnClickListener(view12 -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FORM_URL));
            startActivity(browserIntent);
        });
        String wealthPackageName = "com.fin.onlinetrading";
        String wealthUrl = "https://play.google.com/store/apps/details?id=" + wealthPackageName;
        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager pm = getContext().getPackageManager(); // Assuming you're in an Android context, you'd have a Context object
                if (isAppInstalled(clientPackageName,pm)) {
                    openSplashScreen(clientPackageName);
                } else {
                    redirectToPlayStore(clientPackageName);
                }
            }
        });
        wealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wealthIntent = getContext().getPackageManager().getLaunchIntentForPackage(wealthPackageName);
                if (wealthIntent != null) {
                    wealthIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(wealthIntent);
                } else {
                    // Wealth app is not installed, open Wealth Play Store URL
                    Intent wealthPlayStoreIntent = new Intent(Intent.ACTION_VIEW);
                    wealthPlayStoreIntent.setData(Uri.parse(wealthUrl));
                    wealthPlayStoreIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(wealthPlayStoreIntent);
                }
            }
        });

        return view;
    }
    private void redirectToPlayStore(String packageName) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(clientUrl)));
        }
        // Optional: Close this activity after redirecting to Play Store
    }
    private boolean isAppInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private void openSplashScreen(String packageName) {
        Intent intent = getContext().getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            startActivity(intent);
            // Optional: Close this activity after opening the app
        }
    }
}