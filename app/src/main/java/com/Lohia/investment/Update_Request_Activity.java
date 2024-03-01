package com.Lohia.investment;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Lohia.investment.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Update_Request_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Intent intent;
    String toCheck,policyNumber,gst;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView back;
    LinearLayout selected_img;
    Button upload_button;
    SharedPreferences pre;
    String num,Compney;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request);
        back = findViewById(R.id.backbtn);
        upload_button= findViewById(R.id.upload_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        drawerLayout = findViewById(R.id.drawerLayout2);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);

//        Image get
        selected_img=findViewById(R.id.select_img);
        selected_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageChooser();
            }
        });

        pre = getSharedPreferences("login", MODE_PRIVATE);
        num = pre.getString("num", "data no get yet");


        intent = getIntent();
        if(intent != null) {
            toCheck = intent.getStringExtra("To_CHECK");
        }

    }

    private static final int PICK_IMAGE_REQUEST = 1;

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            ImageView imageView = findViewById(R.id.cloud_icon);
            imageView.setImageURI(selectedImageUri);
            upload_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadImageToFirestore(selectedImageUri);
                    showLoader();
                }
            });
        }
    }

    // Replace this method with your actual upload logic
    private void uploadImageToFirestore(Uri imageUri) {
        // Convert the selected image URI to a Bitmap
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference imagesRef = storageRef.child("images/" + UUID.randomUUID().toString());
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = imagesRef.putBytes(data);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                // Handle successful upload
                // You can get the download URL of the image and save it to Firestore
                imagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    // TODO: Save the download URL to Firestore or perform other operations
                    String downloadUrl = uri.toString();
                    Map<String, Object> imageObject = new HashMap<>();
                    if(toCheck.equals("health")){
                        policyNumber = intent.getStringExtra("POLICY_NUMBER");
                        gst = intent.getStringExtra("GST");
                        int policy_index = intent.getIntExtra("Position", -19); // Use a default value if not found
                        int userIndex = intent.getIntExtra("User_index", -19);
                        imageObject.put("imageUrl", downloadUrl);
                        imageObject.put("policy_type", "health_policy");
                        imageObject.put("policynumber", policyNumber);
                        imageObject.put("gst", gst);
                        imageObject.put("userid", num);
                        imageObject.put("policy_index", policy_index);
                        imageObject.put("user_index", userIndex);
                        imageObject.put("check", "padding");

                    }else if(toCheck.equals("term")){
                        policyNumber = intent.getStringExtra("POLICY_NUMBER");
                        gst = intent.getStringExtra("GST");
                        int policy_index = intent.getIntExtra("Position", -19); // Use a default value if not found
                        int userIndex = intent.getIntExtra("User_index", -19);
                        imageObject.put("policy_type", "term_policy");
                        imageObject.put("imageUrl", downloadUrl);
                        imageObject.put("policynumber", policyNumber);
                        imageObject.put("gst", gst);
                        imageObject.put("userid", num);
                        imageObject.put("policy_index", policy_index);
                        imageObject.put("user_index", userIndex);

                    }else if(toCheck.equals("car")){
                        policyNumber = intent.getStringExtra("POLICY_NUMBER");
                        gst = intent.getStringExtra("GST");
                        Compney = intent.getStringExtra("Compney");
                        int policy_index = intent.getIntExtra("Position", -19); // Use a default value if not found
                        int userIndex = intent.getIntExtra("User_index", -19);
                        imageObject.put("policy_type", "car_policy");
                        imageObject.put("imageUrl", downloadUrl);
                        imageObject.put("policynumber", policyNumber);
                        imageObject.put("gst", gst);
                        imageObject.put("userid", num);
                        imageObject.put("policy_index", policy_index);
                        imageObject.put("user_index", userIndex);

                    }
                    imageObject.put("imageUrl", downloadUrl); // Save the URL to
                    db.collection("Uploaded_Data")
                            .add(imageObject)
                            .addOnSuccessListener(documentReference -> {
                                dismissLoader();
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(this, "Your Screen short Is Uploaded", Toast.LENGTH_LONG).show();
                            })
                            .addOnFailureListener(e -> {
                                dismissLoader();
                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(this, "PLEASE TRY AGAIN YOUR SCREEN SHORT IS NOT UPLOAD Not Save", Toast.LENGTH_LONG).show();
                            });

                });
            }).addOnFailureListener(exception -> {
                dismissLoader();
                Log.d("heheheheheeh","he :"+exception);
                Toast.makeText(this, "PLEASE TRY AGAIN YOUR SCREEN SORT IS NOT UPLOAD", Toast.LENGTH_LONG).show();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_item2_1) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finishAffinity();

        } else if (id == R.id.nav_item2_2) {
            // Handle item 2 click
            startActivity(new Intent(getApplicationContext(),signupActivity.class));
            finishAffinity();
            SharedPreferences pre=getSharedPreferences("login",MODE_PRIVATE);
            SharedPreferences.Editor editor=pre.edit();
            editor.putBoolean("flag",false);
            editor.apply();

        }
        DrawerLayout drawer = findViewById(R.id.drawerLayout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dismissLoader() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    private void showLoader() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading..."); // Set the message you want to display
        progressDialog.setCancelable(false); // Set whether the dialog can be canceled by tapping outside
        progressDialog.show();
    }
}