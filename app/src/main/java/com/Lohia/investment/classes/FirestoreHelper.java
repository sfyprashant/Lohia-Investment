package com.Lohia.investment.classes;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class FirestoreHelper {

    private static final String TAG = "FirestoreHelper";

    // Method to retrieve image URLs from Firestore
    public static void getImageUrlsFromFirestore(OnImageUrlsFetchedListener listener) {
        // Initialize Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Reference to the 'Banner' collection and 'banner' document
        db.collection("Banner").document("banner")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Retrieve image URLs
                                String img1Url = document.getString("img1_url");
                                String img2Url = document.getString("img2_url");
                                String img3Url = document.getString("img3_url");

                                // Create a list to store the image URLs
                                List<String> imageUrlList = new ArrayList<>();
                                imageUrlList.add(img1Url);
                                imageUrlList.add(img2Url);
                                imageUrlList.add(img3Url);

                                // Call the listener with the list of image URLs
                                listener.onImageUrlsFetched(imageUrlList);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
    }

    // Interface to define callback for image URLs fetched
    public interface OnImageUrlsFetchedListener {
        void onImageUrlsFetched(List<String> imageUrlList);
    }
}
