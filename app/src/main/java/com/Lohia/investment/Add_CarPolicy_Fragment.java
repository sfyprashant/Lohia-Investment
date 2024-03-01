package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;
import static com.Lohia.investment.classes.Form.checkEditTextNotEmpty;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.Models.HealthCompanyName;
import com.Lohia.investment.R;
import com.Lohia.investment.classes.CustomSpinner;
import com.Lohia.investment.classes.Form;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Add_CarPolicy_Fragment extends Fragment {

    private String userType,User_name;
    private int index;
    public Add_CarPolicy_Fragment(String userType, int index,String User_name) {
        this.userType = userType;
        this.index = index;
        this.User_name = User_name;
    }

    Spinner one,two,four,eight;
    EditText three,five,six,seven;
    TextView ten,nine;
    String policyfrom,type,policynumber,vehiclcategory,premium,vehicalnumber,make,companyname,policystartdate,policyenddata;
    Button addpolicy;
    CustomSpinner customSpinner,CompnayName,customSpinner2,customSpinner3;
    String[] CompanyArray = HealthCompanyName.getHealthCompany();
    String[] items = new String[]{" ", "Lohia Investments", "Other"};
    String[] items2 = new String[]{" ", "Comprehensive", "Third Party","Standalone"};
    String[] items3 = new String[]{" ", "Two Wheeler", "Private Car", "Commercial Vehicle", "Miscellaneous"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    int ID;

    @Override
    public void onResume() {
        ID=RandomId();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__car_policy_, container, false);
        ID=RandomId();
        one=view.findViewById(R.id.car_show_text_one);
        customSpinner = new CustomSpinner(one, items);
        two=view.findViewById(R.id.car_show_text_two);
        customSpinner2 = new CustomSpinner(two, items2);
        three=view.findViewById(R.id.car_show_text_three);
        four=view.findViewById(R.id.car_show_text_four);
        customSpinner3 = new CustomSpinner(four, items3);
        five=view.findViewById(R.id.car_show_text_five);
        six=view.findViewById(R.id.car_show_text_six);
        seven=view.findViewById(R.id.car_show_text_seven);
        eight=view.findViewById(R.id.car_show_text_nine);
        CompnayName= new CustomSpinner(eight,CompanyArray);
        nine=view.findViewById(R.id.car_show_text_ten);
        ten=view.findViewById(R.id.car_show_text_eleven);
        addpolicy=view.findViewById(R.id.add_policy_btn);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(nine,getContext());
            }
        });
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(ten,getContext());
            }
        });

        addpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                policyfrom = customSpinner.getSelectedValue();
                type = customSpinner2.getSelectedValue();
                policynumber = checkEditTextNotEmpty(three);
                vehiclcategory = customSpinner2.getSelectedValue();
                premium = checkEditTextNotEmpty(five);
                vehicalnumber = checkEditTextNotEmpty(six);
                make = checkEditTextNotEmpty(seven);
                companyname = CompnayName.getSelectedValue();
                policystartdate = nine.getText().toString();
                policyenddata = ten.getText().toString();
                if (policyfrom != null || type != null || policynumber != null || vehiclcategory != null || premium != null || vehicalnumber != null || make != null || companyname != null || policystartdate != null || policyenddata != null) {
                    Map<String, Object> carData = new HashMap<>();
                    carData.put("policyfrom",policyfrom );
                    carData.put("policytype",type );
                    carData.put("policynumber",policynumber );
                    carData.put("vehiclecategory",vehiclcategory );
                    carData.put("vehiclenumber",vehicalnumber );
                    carData.put("premium",premium );
                    carData.put("types",type );
                    carData.put("companyname",companyname);
                    carData.put("model",make );
                    carData.put("startdate",policystartdate );
                    carData.put("enddate",policyenddata );
                    SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
                    String num = pre.getString("num", "data no get yet");
                    if (userType.equals("parent")){
                        carData.put("User_name",User_name);
                        carData.put("title","car_policy");
                        carData.put("select_user",num);
                        carData.put("User_index",-5);
                        AddPolicy(num,carData,ID);
                    }else if (userType.equals("child")){
                        carData.put("User_name",User_name);
                        carData.put("title","car_policy");
                        carData.put("select_user",num);
                        carData.put("User_index",index);
                        AddPolicy(num,carData,ID);
                    }else{
                        Toast.makeText(getContext(), "Something want wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Something went wrong! Please check some fields are empty", Toast.LENGTH_LONG).show();
                }
            }
        });





        return view;
    }

    private void AddPolicy(String userId, Map<String, Object> carData,int IDs) {

        db.collection("USER_ADDED").document(String.valueOf(IDs)).set(carData)
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "Car data added successfully!");
                    clearEditTextFields();
                    ID=RandomId();
                })
                .addOnFailureListener(e -> {
                    // Handle any errors
                    Log.w("TAG", "Error updating health data");
                });
    }


public static int RandomId() {
    Set<Integer> generatedNumbers = new HashSet<>();
    final int MIN_VALUE = 1000;
    final int MAX_VALUE = 9999;
    Random random = new Random();
    int randomNumber;
    do {
        randomNumber = random.nextInt(MAX_VALUE - MIN_VALUE + 1) + MIN_VALUE;
    } while (!generatedNumbers.add(randomNumber) && generatedNumbers.size() < MAX_VALUE - MIN_VALUE + 1);
    if (generatedNumbers.size() == MAX_VALUE - MIN_VALUE + 1) {
        generatedNumbers.clear();
    }

    return randomNumber; // Generates a random number between 1000 and 9999 (inclusive)
}

    private void clearEditTextFields() {
        three.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        nine.setText("");
        ten.setText("");
    }


}