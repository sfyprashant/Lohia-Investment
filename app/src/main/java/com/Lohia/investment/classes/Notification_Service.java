package com.Lohia.investment.classes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.Lohia.investment.R;

public class Notification_Service extends Service {
    String num;
    String checkValue;
    Drawable drawable;
    BitmapDrawable bitmapDrawable;
    Bitmap Largeicon;

    private static final String CHANNEL_ID_NOTIFICATION="NOTIFICATION";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.lohia_logo,null);
        bitmapDrawable= (BitmapDrawable) drawable;
        Largeicon= bitmapDrawable.getBitmap();
        checkValue = intent.getStringExtra("check_5");
        String p_id = intent.getStringExtra("p_id");
        SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
        num = pre.getString("num", "data no get yet");
        if (checkValue.equals("true")) {
            showAcceptNotification(Largeicon,p_id);
        } else {
            showRejectNotification(Largeicon,p_id);
        }

        return START_STICKY; // Or other appropriate return value
    }
    private void showAcceptNotification(Bitmap Largeicon,String p_id) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             notification = new Notification.Builder(this).setLargeIcon(Largeicon)
                    .setSmallIcon(R.drawable.lohia_logo)
                    .setContentTitle("Payment Receipt Accepted")
                    .setSubText("This Massage from Lohia Admin Policy Number is "+p_id)
                    .setChannelId(CHANNEL_ID_NOTIFICATION)
                    .build();
        }else{
            notification = new Notification.Builder(this).setLargeIcon(Largeicon)
                    .setSmallIcon(R.drawable.lohia_logo)
                    .setContentTitle("Payment Receipt Accepted")
                    .setSubText("This Massage from Lohia Admin Policy Number is "+p_id)
                    .build();
        }
    }
    private void showRejectNotification(Bitmap Largeicon,String p_id) {
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this).setLargeIcon(Largeicon)
                    .setSmallIcon(R.drawable.lohia_logo)
                    .setContentTitle("Payment Receipt Rejected")
                    .setSubText("This Massage from Lohia Admin Policy Number is "+p_id)
                    .setChannelId(CHANNEL_ID_NOTIFICATION)
                    .build();
        }else{
            notification = new Notification.Builder(this).setLargeIcon(Largeicon)
                    .setSmallIcon(R.drawable.lohia_logo)
                    .setContentTitle("Payment Receipt Rejected")
                    .setSubText("This Massage from Lohia Admin Policy Number is "+p_id)
                    .build();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
