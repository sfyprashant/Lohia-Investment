package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.Lohia.investment.Adapter.Image_adapter;
import com.Lohia.investment.Models.All_Data;
import com.Lohia.investment.Models.Child;
import com.Lohia.investment.Models.Notification_list;
import com.Lohia.investment.R;
import com.Lohia.investment.classes.FirestoreHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    Button Health,Car,term;
    Image_adapter image_adapter;
    private int currentPage = 0;
    private LinearLayout dotsLayout;
    private Timer timer;
    private Handler handler;
    ViewPager2 mviewpager; // Change to ViewPager2
    Button insur,invest;
    TextView marqueeText;
    Child child = new Child();
    String number;
    List<Notification_list> notification_lists = new ArrayList<>();
    ImageView dot1,dot2,dot3;
    ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences pre= getActivity().getSharedPreferences("login", MODE_PRIVATE);
        number= pre.getString("num","defaultStringIfNothingFound");
        marqueeText=view.findViewById(R.id.notification_text);
        handler = new Handler(Looper.getMainLooper());
        marqueeText.setSelected(true);
        Health = view.findViewById(R.id.health);
        Car = view.findViewById(R.id.car);
        dotsLayout = view.findViewById(R.id.dotsLayout);
        term = view.findViewById(R.id.term);
        dot1 = view.findViewById(R.id.dot1);
        dot2 = view.findViewById(R.id.dot2);
        dot3 = view.findViewById(R.id.dot3);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading..."); // Set your message here
        progressDialog.setCancelable(false);
        progressDialog.show();
        term.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventChangeListener("term_policy");
            }
        });
        Car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventChangeListener("car_policy");
            }
        });
        Health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventChangeListener("health_policy");
            }
        });

        invest=view.findViewById(R.id.investmentButton);
        invest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insur.setBackgroundResource(R.drawable.bluebtnsh);
                invest.setBackgroundResource(R.drawable.shapeinsurance);
                startActivity(new Intent(getContext(),InvestmentActivity.class));
            }
        });
        insur=view.findViewById(R.id.insuranceButton);
        insur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insur.setBackgroundResource(R.drawable.greenbtnsh);
                invest.setBackgroundResource(R.drawable.shapepolicy);
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });
        List<Integer> imagesList = new ArrayList<>();
        imagesList.add(R.drawable.i1);
        imagesList.add(R.drawable.i2);
        imagesList.add(R.drawable.i3);

        CollectionReference usersCollection = db.collection("users");
        SharedPreferences pre1= getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String num=pre1.getString("num","No Data");
        DocumentReference userDocument = usersCollection.document(num);
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document=task.getResult();
                    if (document.exists()){
                        Map<String, Object> data = document.getData();
                        String name=(String) data.get("name");
                        ArrayList<Map<String, Object>> carPolicyList = (ArrayList<Map<String, Object>>) data.get("car_policy");
                        try{
                        for (Map<String, Object> carPolicyData : carPolicyList) {
                            String enddate = (String) carPolicyData.get("enddate");
                            String policynumber = (String) carPolicyData.get("policynumber");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = null;
                            try {
                                date = dateFormat.parse(enddate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            notification_lists.add(new Notification_list(name,policynumber,date));
                            startCountdown(notification_lists);
                        }}catch (Exception e){
                            e.printStackTrace();
                        }
                        ArrayList<Map<String, Object>> healthPolicyList = (ArrayList<Map<String, Object>>) data.get("health_policy");
                        try{
                        for (Map<String, Object> healthData : healthPolicyList) {
                            String enddate= (String) healthData.get("policyenddate");
                            String policynumber = (String) healthData.get("policynumber");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = null;
                            try {
                                date = dateFormat.parse(enddate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            notification_lists.add(new Notification_list(name,policynumber,date));
                            startCountdown(notification_lists);
                        }}catch (Exception e){
                            e.printStackTrace();
                        }
                        ArrayList<Map<String, Object>> termPolicyList = (ArrayList<Map<String, Object>>) data.get("term_policy");
                        try{
                        for (Map<String, Object> termData : termPolicyList) {
                            String premiumpaymentupto = (String) termData.get("premiumpaymentupto");
                            String policynumber = (String) termData.get("policynumber");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date date = null;
                            try {
                                date = dateFormat.parse(premiumpaymentupto);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            notification_lists.add(new Notification_list(name,policynumber,date));
                            startCountdown(notification_lists);
                        }}catch (Exception e){
                            e.printStackTrace();
                        }
                    }}}});

        mviewpager = view.findViewById(R.id.image_slider);
        FirestoreHelper.getImageUrlsFromFirestore(new FirestoreHelper.OnImageUrlsFetchedListener() {
            @Override
            public void onImageUrlsFetched(List<String> imageUrlList) {
                image_adapter=new Image_adapter(getContext(),imageUrlList);
                mviewpager.setAdapter(image_adapter);
                progressDialog.dismiss();
            }
        });




        mviewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDots(position);
            }
        });
        // Start auto-sliding images
        startAutoSlider();
        return view;
    }
    private void updateDots(int position) {
        int dotCount = 3;
        ImageView[] dots = new ImageView[dotCount];


        dots[0] = dot1;
        dots[1] = dot2;
        dots[2] = dot3;

        // Set all dots to unselected state initially
        for (ImageView dot : dots) {
            dot.setImageResource(R.drawable.dot_unselected);
        }

        if (dots.length > 0 && position < dots.length) {
            dots[position].setImageResource(R.drawable.dot_selected);
        }

    }

    private void startAutoSlider() {
        final Handler handler = new Handler(Looper.getMainLooper());

        final Runnable update = new Runnable() {
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0;
                }
                mviewpager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 2000, 2000); // Change images every 2 seconds (adjust as needed)
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    private void EventChangeListener(String check) {
        CollectionReference usersCollection = db.collection("users");
        SharedPreferences pre= getActivity().getSharedPreferences("login", MODE_PRIVATE);
        String num=pre.getString("num","No Data");
        DocumentReference userDocument = usersCollection.document(num);
        userDocument.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document=task.getResult();
                    if (document.exists()){
                        Map<String, Object> data = document.getData();
                       All_Data userData=new All_Data();
                        userData.naturework = (String) data.get("naturework");
                        userData.channel = (String) data.get("channel");
                        userData.work_profile = (String) data.get("work_profile");
                        userData.address= (String) data.get("address");
                        userData.fathername= (String) data.get("fathername");
                        userData.name= (String) data.get("name");
                        userData.dob= (String) data.get("dob");
                        userData.number= (String) data.get("number");
                        userData.gander= (String) data.get("gander");
                        userData.usertype= (String) data.get("usertype");
                        userData.email= (String) data.get("email");
                        Intent intent=new Intent(getContext(),TreeShowActivity.class);
                        intent.putExtra("check",check);
                        intent.putExtra("number",number);
                        intent.putExtra("child",child);
                        intent.putExtra("userData",userData);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Your Data no exist -1", Toast.LENGTH_SHORT).show();
                    }
            } else {
                Toast.makeText(getContext(), "Your Data no exist -2", Toast.LENGTH_SHORT).show();
            }
        }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("ERROR",""+e);
            }
        });

    }

    private void startCountdown(final List<Notification_list> policies) {
        Date currentDate = new Date();
        for (Notification_list policy : policies) {
            Date targetDate = policy.getEndDate();
            String Name =policy.getNameuser();
            String num =policy.getPolicyNumber();
            String arrayDate = null;
            String Current_Date =null;
            Date currentDateFormatted = null;
            Date targetDateFormatted = null;
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM", Locale.ENGLISH);
            try {
                Current_Date = outputFormat.format(currentDate);
                arrayDate = outputFormat.format(targetDate);
                currentDateFormatted = outputFormat.parse(Current_Date);
                targetDateFormatted = outputFormat.parse(arrayDate);
            } catch (Exception e) {
                e.printStackTrace();
            }

          Long  remainingMillis = targetDateFormatted.getTime() - currentDateFormatted.getTime();;
            if (remainingMillis >= 0) {
                int remainingDays = (int) (remainingMillis/ (1000 * 60 * 60 * 24));
                if (remainingDays <= 30 && remainingDays > 0) {
                    String message ="Notice :- "+ Name+" your policy ends in " + remainingDays + " days " +"You Policy Number "+num ;
                    marqueeText.setText(message);
                    marqueeText.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getContext(), "Please check your policy and pay", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Exit the loop after setting the notification
                    return;
                }
            }
        }

        // Schedule the next update (every day)
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Call startCountdown again after 24 hours
                startCountdown(policies);
            }
        }, 24 * 60 * 60 * 1000); // 24 hours
    }

}













































//    private void startCountdown(List<Notification_list> policies) {
//        // Create a Runnable to update the text view periodically
//        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        final Runnable updateRunnable = new Runnable() {
//            @Override
//            public void run() {
//                Calendar currentDate = Calendar.getInstance();
//                for (Notification_list policy : policies) {
//                    Date targetDate = policy.getEndDate();
//                    long remainingMillis = targetDate.getTime() - currentDate.getTimeInMillis();
//                    if (remainingMillis >= 0) {
//                        int remainingDays = (int) (remainingMillis / (24 * 60 * 60 * 1000));
//                        String message = policy.getNameuser() + " Policy End Your Policy Number " +
//                                policy.getPolicyNumber() + " " + remainingDays + " days";
//                        notification.setText(message);
//                        notification.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(getContext(), "Please check your policy and pay", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        return; // Exit the loop after setting the first notification
//                    }
//                }
//                // Schedule the next update (every day)
//                handler.postDelayed(this, 24 * 60 * 60 * 1000); // 24 hours
//            }
//        };
//
//        // Initial run to update the text
//        updateRunnable.run();
//    }



