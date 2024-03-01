package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.Models.BankListGenerator;
import com.Lohia.investment.Models.HealthCompanyName;
import com.Lohia.investment.classes.CustomSpinner;
import com.Lohia.investment.classes.CustomSpinner2;
import com.Lohia.investment.classes.Form;
import com.Lohia.investment.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;


public class Add_HealthPolicy_Fragment extends Fragment {
    private String userType,User_name;
    private int index;

    public Add_HealthPolicy_Fragment(String userType, int index,String User_name) {
        this.userType = userType;
        this.index = index;
        this.User_name = User_name;
    }

    Spinner one,two,eleven;
    EditText  three, four, five, six, seven,twelve;
    TextView eight, nine, tan;
    String[] CompanyArray = HealthCompanyName.getHealthCompany();
    String policyfrom, policytype, policynumber, firstpolicynumber, gst, suma, ttd, firstpolicydate, policystartdate, policyenddate,CompanyName,MamberInsured;
    Button addpolicy,addmamber;
    CustomSpinner customSpinner,customSpinnercompnay;
    CustomSpinner2 customSpinner2;
    String[] items = new String[]{" ", "Lohia Investments", "Other"};
    String[] items2 = new String[]{" ", "Base", "Super Topup","Critical Illness",  "Personal Accident", "Travel"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private LinearLayout memberContainer;
    private String[] member_Array = new String[0];
    int ID;
    @Override
    public void onResume() {
        ID=RandomId();
        super.onResume();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__health_policy_, container, false);
        ID=RandomId();
        one = view.findViewById(R.id.policy_form_text_field_one);
        customSpinner = new CustomSpinner(one, items);
        two = view.findViewById(R.id.ploicy_form_text_field_two);
        TextView TTD_Change = view.findViewById(R.id.TTD_Change);
        TextView sum_chanage = view.findViewById(R.id.sum_chanage);
        three = view.findViewById(R.id.policy_form_text_field_three);
        four = view.findViewById(R.id.policy_form_text_field_four);
        five = view.findViewById(R.id.policy_form_text_field_five);
        six = view.findViewById(R.id.policy_form_text_field_six);
        seven = view.findViewById(R.id.policy_form_text_field_seven);
        eight = view.findViewById(R.id.policy_form_text_field_eight);
        nine = view.findViewById(R.id.policy_form_text_field_nine);
        tan = view.findViewById(R.id.policy_form_text_field_ten);
        eleven = view.findViewById(R.id.policy_form_text_field_eleven);
        customSpinnercompnay = new CustomSpinner(eleven,CompanyArray);



        twelve = view.findViewById(R.id.policy_form_text_field_twelve);
        addmamber = view.findViewById(R.id.add_mamber);
        memberContainer = view.findViewById(R.id.items_container);

        addpolicy = view.findViewById(R.id.add_policy);
        customSpinner2 = new CustomSpinner2(two,sum_chanage,TTD_Change,seven,items2);
        TextView PremiumWithout = view.findViewById(R.id.PremiumWithout);

        addmamber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = twelve.getText().toString().trim();
                if (!newItem.isEmpty()) {
                    addItem(newItem);
                    twelve.setText(""); // Clear the EditText after adding
                }
            }
        });

        PremiumWithout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value_PremiumWithout = view.findViewById(R.id.value_PremiumWithout);
                if (!five.getText().toString().isEmpty()) {
                    String fiveText = Form.checkEditTextNotEmpty(five);
                    assert fiveText != null;
                    Float GST = Float.parseFloat(fiveText);
                    Float test = 1.18f;
                    Float result = GST / test;
                    String formattedResult = String.format("%.2f", result);
                    value_PremiumWithout.setVisibility(View.VISIBLE);
                    value_PremiumWithout.setText(formattedResult);
                } else {
                    Toast.makeText(getContext(), "Please Select Enter Any Value", Toast.LENGTH_SHORT).show();
                    value_PremiumWithout.setVisibility(View.GONE);
                }

            }
        });
        // Inflate the layout for this fragment
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(eight,getContext());
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(nine,getContext());;
            }
        });
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(tan,getContext());
            }
        });
        addpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                policyfrom = customSpinner.getSelectedValue();
                policytype = customSpinner2.getSelectedValue();
                policynumber = Form.checkEditTextNotEmpty(three);
                firstpolicynumber = Form.checkEditTextNotEmpty(four);
                gst = Form.checkEditTextNotEmpty(five);
                suma = Form.checkEditTextNotEmpty(six);
                ttd = Form.checkEditTextNotEmpty(seven);
                firstpolicydate = eight.getText().toString();
                policystartdate = nine.getText().toString();
                policyenddate = tan.getText().toString();
                CompanyName = customSpinnercompnay.getSelectedValue();
                MamberInsured = memberstring(member_Array);
                if (policyfrom != null || policytype != null || policynumber != null || firstpolicynumber != null || gst != null || suma != null || ttd != null || firstpolicydate != null || policystartdate != null || policyenddate != null) {
                    Map<String, Object> healthData = new HashMap<>();
                    healthData.put("policyfrom",policyfrom );
                    healthData.put("policytype",policytype );
                    healthData.put("policynumber",policynumber );
                    healthData.put("firstpolicynumber",firstpolicynumber );
                    healthData.put("gst",gst );
                    healthData.put("suma",suma );
                    healthData.put("ttd",ttd );
                    healthData.put("fistpolicydate",firstpolicydate );
                    healthData.put("policystartdate",policystartdate );
                    healthData.put("policyenddate",policyenddate );
                    healthData.put("companyname",CompanyName );
                    healthData.put("membersinsured",MamberInsured );
                    healthData.put("check",true );
                    SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
                    String num = pre.getString("num", "data no get yet");
                    if (userType.equals("parent")){
                        healthData.put("User_name",User_name);
                        healthData.put("title","health_policy");
                        healthData.put("select_user",num);
                        healthData.put("User_index",-5);
                        AddPolicy(num,healthData,ID);
                    }else if (userType.equals("child")){
                        healthData.put("User_name",User_name);
                        healthData.put("title","health_policy");
                        healthData.put("select_user",num);
                        healthData.put("User_index",index);
                        AddPolicy(num,healthData,ID);
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

//    Member Array Function Opening
private void addItem(String newItem) {
    // Increase array size and add new item
    String[] newArray = new String[member_Array.length + 1];
    System.arraycopy(member_Array, 0, newArray, 0, member_Array.length);
    newArray[newArray.length - 1] = newItem;
    member_Array = newArray;

    // Create a new view for the item
    createItemView(newItem);
}
    private void createItemView(String item) {
        LinearLayout itemLayout = new LinearLayout(getContext());
        itemLayout.setOrientation(LinearLayout.HORIZONTAL);
        Drawable backDrawable = ContextCompat.getDrawable(getContext(), R.drawable.add_member_bg);
        itemLayout.setBackground(backDrawable);
        itemLayout.setPadding(10,10,10,10);



        TextView itemText = new TextView(getContext());
        itemText.setText(item);

        ImageView deleteButton = new ImageView(getContext());
        Drawable closeDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_baseline_cancel_24);
        deleteButton.setImageDrawable(closeDrawable);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(item);
            }
        });

        itemLayout.addView(itemText);
        itemLayout.addView(deleteButton);

        memberContainer.addView(itemLayout);
    }
    private void removeItem(String itemToRemove) {
        // Find the item index in the array
        int index = -1;
        for (int i = 0; i < member_Array.length; i++) {
            if (member_Array[i].equals(itemToRemove)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Create a new array without the removed item
            String[] newArray = new String[member_Array.length - 1];
            System.arraycopy(member_Array, 0, newArray, 0, index);
            System.arraycopy(member_Array, index + 1, newArray, index, member_Array.length - index - 1);

            // Update member_Array to the new array
            member_Array = newArray;

            // Remove the view from the container
            memberContainer.removeViewAt(index);
        } else {
            Log.d("Member data", "No Member");
        }

    }


    private String memberstring(String[] memberarray){
        StringBuilder joinedString = new StringBuilder();
        for (String item : memberarray) {
            joinedString.append(item);
            joinedString.append(", ");
        }

        if (joinedString.length() > 0) {
            joinedString.delete(joinedString.length() - 2, joinedString.length());
        }

        return joinedString.toString();
    }

//    Member Array Function ending
    private void AddPolicy(String userId, Map<String, Object> carData,int IDS) {

        db.collection("USER_ADDED").document(String.valueOf(IDS)).set(carData)
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
    four.setText("");
    five.setText("");
    six.setText("");
    seven.setText("");
    eight.setText("");
    nine.setText("");
    tan.setText("");
}


}

