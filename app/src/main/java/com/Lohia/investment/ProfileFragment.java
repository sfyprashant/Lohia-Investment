package com.Lohia.investment;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.Lohia.investment.Adapter.UploadImageAdapter;
import com.Lohia.investment.Adapter.User_profile_adapter;
import com.Lohia.investment.Models.User_profile_model;
import com.Lohia.investment.Models.uploadmodel;
import com.Lohia.investment.R;
import com.Lohia.investment.classes.Notification_Service;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProfileFragment extends Fragment {

    private ListView listView;
    private List<String> imagesList;
    private List<String> check;
    private UploadImageAdapter adapter;
    ArrayList<User_profile_model> user_profile_models = new ArrayList<>();
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    List<uploadmodel> uploadmodellist = new ArrayList<>();
    ProgressDialog progressDialog;
    User_profile_adapter adapter2;
    TextView name,health,car,term,health_cover,life_cover;
    Button add_policy;
    String userName,FORM_URL="https://forms.gle/aHs2FfLZi8vP56Jw8";
    Button google_form;
    int healthsum = 0;
    int lifesum = 0;

    TextView see_more;
    String num;
    LinearLayout image_list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Notification_Service();
        listView = view.findViewById(R.id.upload_images);
        name=view.findViewById(R.id.textView_one);
        health=view.findViewById(R.id.health_policy_number);
        term=view.findViewById(R.id.life_policy_number);
        car=view.findViewById(R.id.motar_policy_number);
        google_form=view.findViewById(R.id.google_form);
        add_policy=view.findViewById(R.id.add_policy_btn);
        health_cover=view.findViewById(R.id.health_cover);
        life_cover=view.findViewById(R.id.life_cover);
        image_list=view.findViewById(R.id.pf_image_list);
        see_more=view.findViewById(R.id.pf_see_more);
        add_policy.setOnClickListener(view1 -> {
            Intent intent= new Intent(getContext(),Add_Policy_Activity.class);
            intent.putExtra("name",userName);
            intent.putExtra("usertype","parent");
            intent.putExtra("position", 100);
            startActivity(intent);
        });

        google_form.setOnClickListener(view12 -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FORM_URL));
            startActivity(browserIntent);
        });
        //loder
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..."); // Set your message here
        progressDialog.setCancelable(false);
//        loder
        RecyclerView recyclerView2 = view.findViewById(R.id.child_data);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        imagesList = new ArrayList<>();
        check = new ArrayList<>();
        SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
        num = pre.getString("num", "data no get yet");

        db.collection("Uploaded_Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                uploadmodel data = document.toObject(uploadmodel.class);
                                if (data.userid.equals(num)) {
                                    uploadmodellist.add(data);
                                    imagesList.add(data.imageUrl);
                                    check.add(data.check);
                                }
                            }
                            adapter = new UploadImageAdapter(getContext(), imagesList,check);
                            listView.setAdapter(adapter);
                            progressDialog.dismiss();
                            image_list.setVisibility(View.VISIBLE);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            image_list.setVisibility(View.GONE);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("hellooooo", "" + e);

                    }
                });

        progressDialog.show();

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action= motionEvent.getAction();
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                view.onTouchEvent(motionEvent);
                return true;
            }
        });



//        USER AND CHILD DATA

        try {
            CollectionReference usersCollection = db.collection("users");
            DocumentReference userDocument = usersCollection.document(num);
            userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                 @Override
                 public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                     if (task.isSuccessful()) {
                         DocumentSnapshot document = task.getResult();
                         if (document.exists()) {
                             Map<String, Object> data = document.getData();
                             userName = data.get("name").toString();
                             String childName;
                             int healthPolicyCount = 0;
                             int carPolicyCount = 0;
                             int termPolicyCount = 0;
                             List<Map<String, Object>> healthPolicies = (List<Map<String, Object>>) data.get("health_policy");
                             healthPolicyCount=healthPolicies.size();
                             String[] sumaValues = new String[healthPolicies.size()];
                             for (int i = 0; i < healthPolicies.size(); i++) {
                                 Map<String, Object> policy = healthPolicies.get(i);
                                 if (policy != null && policy.containsKey("suma")) {
                                     Object sumaObject = policy.get("suma");
                                     sumaValues[i] = String.valueOf(sumaObject);
                                 } else {
                                     sumaValues[i] = "0"; // Or any other default value you want to assign
                                 }
                             }

                             for (int i = 0; i < sumaValues.length; i++) {
                                 float floatValue= Float.parseFloat(sumaValues[i]);
                                 healthsum += floatValue;
                             }

                             health_cover.setText(String.valueOf(healthsum));
                             List<Map<String, Object>> carPolicies = (List<Map<String, Object>>) data.get("car_policy");
                             if (carPolicies != null) {
                                 carPolicyCount = carPolicies.size();
                             }
                             List<Map<String, Object>> termPolicies = (List<Map<String, Object>>) data.get("term_policy");
                             if (termPolicies != null) {
                                 termPolicyCount = termPolicies.size();
                                 String[] sumaValueslife = new String[termPolicies.size()];
                                 for (int i = 0; i < termPolicies.size(); i++) {
                                     Map<String, Object> policy = termPolicies.get(i);
                                     if (policy != null && policy.containsKey("suma")) {
                                         Object sumaObject = policy.get("suma");
                                         sumaValueslife[i] = String.valueOf(sumaObject);
                                     } else {
                                         sumaValueslife[i] = "0"; // Or any other default value you want to assign
                                     }
                                 }
                                 for (int i = 0; i < sumaValueslife.length; i++) {
                                     float floatValuelife= Float.parseFloat(sumaValueslife[i]);
                                     lifesum += floatValuelife;
                                 }
                                 life_cover.setText(String.valueOf(lifesum));
                             }
                             name.setText(userName);
                             health.setText(String.valueOf(healthPolicyCount));
                             car.setText(String.valueOf(carPolicyCount));
                             term.setText(String.valueOf(termPolicyCount));

                             List<Map<String, Object>> childrenList = (List<Map<String, Object>>) data.get("children");
                             if (childrenList != null) {
                                 String heath ,car ,term ;
                                 for (Map<String, Object> child : childrenList) {
                                     childName = (String) child.get("name");
                                     int child_healthPolicyCount = 0;
                                     int child_carPolicyCount = 0;
                                     int child_termPolicyCount = 0;
                                     int childhealthsum =0;
                                     int childlifesum=0;
                                     List<Map<String, Object>> child_healthPolicies = (List<Map<String, Object>>) child.get("health_policy");
                                     if (child_healthPolicies != null) {
                                         child_healthPolicyCount= child_healthPolicies.size();
                                         String[] childsumaValueslife = new String[child_healthPolicies.size()];
                                         for (int i = 0; i < child_healthPolicies.size(); i++) {
                                             Map<String, Object> policy = child_healthPolicies.get(i);
                                             if (policy != null && policy.containsKey("suma")) {
                                                 Object sumaObject = policy.get("suma");
                                                 childsumaValueslife[i] = String.valueOf(sumaObject);
                                             } else {
                                                 childsumaValueslife[i] = "0"; // Or any other default value you want to assign
                                             }
                                         }
                                         for (int i = 0; i < childsumaValueslife.length; i++) {
                                             float floatValuelife= Float.parseFloat(childsumaValueslife[i]);
                                             childhealthsum += floatValuelife;
                                         }
                                     }
                                     List<Map<String, Object>> child_carPolicies = (List<Map<String, Object>>) child.get("car_policy");
                                     if (child_carPolicies != null) {
                                         child_carPolicyCount= child_carPolicies.size();
                                     }
                                     List<Map<String, Object>> child_termPolicies = (List<Map<String, Object>>) child.get("term_policy");
                                     if (child_termPolicies != null) {
                                         child_termPolicyCount= child_termPolicies.size();
                                         String[] childsumaValueshealth = new String[child_termPolicies.size()];
                                         for (int i = 0; i < child_termPolicies.size(); i++) {
                                             Map<String, Object> policy = child_termPolicies.get(i);
                                             if (policy != null && policy.containsKey("suma")) {
                                                 Object sumaObject = policy.get("suma");
                                                 childsumaValueshealth[i] = String.valueOf(sumaObject);
                                             } else {
                                                 childsumaValueshealth[i] = "0"; // Or any other default value you want to assign
                                             }
                                         }
                                         for (int i = 0; i < childsumaValueshealth.length; i++) {
                                             float floatValuelife= Float.parseFloat(childsumaValueshealth[i]);
                                             childlifesum += floatValuelife;
                                         }
                                     }
                                     heath= String.valueOf(child_healthPolicyCount);
                                     car= String.valueOf(child_carPolicyCount);
                                     term= String.valueOf(child_termPolicyCount);
                                     User_profile_model users = new User_profile_model(childName,heath,car,term,childhealthsum,childlifesum);
                                     user_profile_models.add(users);
                                     adapter2 = new User_profile_adapter(getContext(), user_profile_models); // Pass your data list
                                     recyclerView2.setAdapter(adapter2);
                                 }
                             }
                             else {
                                 recyclerView2.setVisibility(View.GONE);
                                 see_more.setVisibility(View.GONE);

                             }
                         }
                     }
                 }
             }
            );

        }catch (Exception e){
            startActivity(new Intent(getContext(),MainActivity.class));
        }
        return view;
    }
    public void Notification_Service(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Uploaded_Data")
                .whereEqualTo("userid", num)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Error listening for updates.", e);
                        return;
                    }
                    if (queryDocumentSnapshots != null && !queryDocumentSnapshots.isEmpty()) {
                        // Iterate through the documents
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            // Assuming "check" is a boolean field in your document
                            String checkValue = document.getString("check");
                            String P_id = document.getString("policynumber");

                            if (!checkValue.isEmpty()) {
                                startBackgroundService(getContext(),checkValue,P_id);
                            }
                        }
                    }
                });
    }

    private static void startBackgroundService(Context context,String checkValue,String p_id) {
        Intent serviceIntent = new Intent(context, Notification_Service.class);
        serviceIntent.putExtra("check_5",checkValue);
        serviceIntent.putExtra("p_id",p_id);
        context.startService(serviceIntent);
    }
}
