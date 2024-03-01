package com.Lohia.investment.classes;




import android.app.DownloadManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Toast;



public class FileDownloader {
    public static void downloadFile(String fileUrl, Context context) {
        DownloadManager.Request request =new DownloadManager.Request(Uri.parse(fileUrl));
        String title= URLUtil.guessFileName(fileUrl,null,null);
        request.setTitle(title);
        request.setDescription("Downloading File Please Wait....");
        String cookie= CookieManager.getInstance().getCookie(fileUrl);
        request.addRequestHeader("cookie",cookie);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);

        DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);

        Toast.makeText(context, "Downloading Started...", Toast.LENGTH_SHORT).show();
    }

}