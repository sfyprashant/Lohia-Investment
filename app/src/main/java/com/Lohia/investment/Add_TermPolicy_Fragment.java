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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.Models.HealthCompanyName;
import com.Lohia.investment.Models.LifeCompanyName;
import com.Lohia.investment.R;
import com.Lohia.investment.classes.CustomSpinner;
import com.Lohia.investment.classes.CustomSpinner3;
import com.Lohia.investment.classes.CustomSpinner4;
import com.Lohia.investment.classes.Form;
import com.Lohia.investment.classes.customSpinnerBANK;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class Add_TermPolicy_Fragment extends Fragment {

    private String userType,User_name;
    private int index;
    public Add_TermPolicy_Fragment(String userType, int index,String User_name) {
        this.userType = userType;
        this.index = index;
        this.User_name = User_name;
    }
    Spinner one,two,three,six,seventeen;
    String[] CompanyArray = LifeCompanyName.getLifeCompany();
    EditText  four, five, nine, ten,eleven,twelve,fifteen,sixteen,eighteen,BankText;
    TextView seven,eight,thirteen,fourteen;
    String policyfrom,policytype,compenyname,policynumber,mode,firstunpaidpremium,gst,suma,planename,term,ppterm,dateofcommencement,premiumpaymentupto,approxmaturityamount,maturitydate,paymentmode,premiumwithoutgst,ABOUTBANK;
    Button addpolicy;
    TextView Title_PPU;
    CustomSpinner customSpinner,CompanySpinner;
    customSpinnerBANK customSpinnerbank;
    LinearLayout BANKLAYOUT;
    CustomSpinner4 customSpinner2;
    CustomSpinner3 customSpinner3;
    String[] items = new String[]{" ", "Lohia Investments", "Other"};
    String[] items2 = new String[]{" ", "Bonus","Term","ULIP","Money back", "Guaranteed"};
    String[] items3 = new String[]{" ", "Single","Monthly","Quarterly","Half-Yearly", "Annually"};
    String[] items4 = new String[]{" ", "ECS","Cash/Cheque"};
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add__term_policy_, container, false);
        int ID=RandomId();
        one=view.findViewById(R.id.term_policy_form_title_one);
        customSpinner = new CustomSpinner(one, items);
        two=view.findViewById(R.id.term_policy_form_title_two);
        three=view.findViewById(R.id.term_policy_form_title_three);
        CompanySpinner = new CustomSpinner(three,CompanyArray);
        TextView PremiumWithGST=view.findViewById(R.id.PremiumWithGST);
//        four=view.findViewById(R.id.term_policy_form_title_four);
        five=view.findViewById(R.id.term_policy_form_title_five);
        six=view.findViewById(R.id.term_policy_form_title_six);
        seven=view.findViewById(R.id.term_policy_form_title_seven);
        eight=view.findViewById(R.id.term_policy_form_title_eight);
        nine=view.findViewById(R.id.term_policy_form_title_nine);
        ten=view.findViewById(R.id.term_policy_form_title_ten);
        eleven=view.findViewById(R.id.term_policy_form_title_eleven);
        twelve=view.findViewById(R.id.term_policy_form_title_twe);
        thirteen=view.findViewById(R.id.term_policy_form_title_thirteen);
        fourteen=view.findViewById(R.id.term_policy_form_title_forteen);
        fifteen=view.findViewById(R.id.term_policy_form_title_fifteen);
        sixteen=view.findViewById(R.id.term_policy_form_title_sixteen);
        seventeen=view.findViewById(R.id.term_policy_form_title_seventeen);
        BankText=view.findViewById(R.id.term_policy_form_title_BANK);
        BANKLAYOUT =view.findViewById(R.id.BANK_LAYOUT);
        eighteen=view.findViewById(R.id.term_policy_form_title_eighteen);
        Title_PPU =view.findViewById(R.id.title_term_policy_form_title_forteen);
        customSpinner2 = new CustomSpinner4(two,items2,fifteen);
        customSpinner3 = new CustomSpinner3(six, items3, seven, twelve, Title_PPU, fourteen);
        customSpinnerbank = new customSpinnerBANK(seventeen,items4,BANKLAYOUT);
        addpolicy=view.findViewById(R.id.add_policy_btn);
//        Calculation based on type
        PremiumWithGST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected = customSpinner2.getSelectedValue();
                float WithoutGST= Float.parseFloat(eighteen.getText().toString());
                if (!eighteen.getText().toString().isEmpty()){
                    if(selected.equals("Bonus")){
                        float result = WithoutGST * (1 + 1.0225f / 100);
                        String resultString = String.valueOf(result);
                        eight.setText(resultString);
                    } else if (selected.equals("Money back")) {
                        float result = WithoutGST * (1 + 1.0225f / 100);
                        String resultString = String.valueOf(result);
                        eight.setText(resultString);
                    } else if (selected.equals("Guaranteed")) {
                        float result = WithoutGST * (1 + 1.0225f / 100);
                        String resultString = String.valueOf(result);
                        eight.setText(resultString);
                    } else if (selected.equals("Term")) {
                        float result1 = WithoutGST *  1.18f;
                        String resultString = String.valueOf(result1);
                        eight.setText(resultString);
                    } else if (selected.equals("ULIP")) {
                        eight.setText(eighteen.getText().toString());
                    } else {
                        Toast.makeText(getContext(), "Please Enter Please Select Policy Type", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "Please Enter Premium Without GST", Toast.LENGTH_SHORT).show();
                }
            }
        });





        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(seven,getContext());
            }
        });
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(thirteen,getContext());
            }
        });
        fourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(fourteen,getContext());
            }
        });
        addpolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                policyfrom = customSpinner.getSelectedValue();
                policytype = customSpinner2.getSelectedValue();
                compenyname = CompanySpinner.getSelectedValue();
//                type = checkEditTextNotEmpty(four);
                policynumber = checkEditTextNotEmpty(five);
                mode = customSpinner3.getSelectedValue();
                firstunpaidpremium = seven.getText().toString();
                gst = eight.getText().toString();
                suma = checkEditTextNotEmpty(nine);
                planename = checkEditTextNotEmpty(ten);
                term = checkEditTextNotEmpty(eleven);
                ppterm = checkEditTextNotEmpty(twelve);
                dateofcommencement = thirteen.getText().toString();
                premiumpaymentupto = fourteen.getText().toString();
                approxmaturityamount = checkEditTextNotEmpty(fifteen);
                maturitydate = checkEditTextNotEmpty(sixteen);
                paymentmode = customSpinnerbank.getSelectedValue();
                if (BankText.getText().toString().trim()!= null){
                    ABOUTBANK = BankText.getText().toString().trim();
                }else{
                    ABOUTBANK="NO";
                }

                premiumwithoutgst = checkEditTextNotEmpty(eighteen);
                if (policyfrom != null || policytype != null || compenyname != null ||  policynumber != null || mode != null || firstunpaidpremium != null || gst != null || suma != null || planename != null || term != null|| ppterm != null || dateofcommencement != null || premiumpaymentupto != null || approxmaturityamount != null || maturitydate != null || paymentmode != null || premiumwithoutgst != null) {
                    Map<String, Object> termData = new HashMap<>();
                    termData.put("check",true );
                    termData.put("policyfrom",policyfrom );
//                    termData.put("types",type );
                    termData.put("policynumber",policynumber );
                    termData.put("companyname",compenyname );
                    termData.put("premiumgst",true );
                    termData.put("premiumwithoutgst",premiumwithoutgst );
                    termData.put("mode",mode );
                    termData.put("firstunpaidpremium",firstunpaidpremium );
                    termData.put("planname",planename );
                    termData.put("term",term );
                    termData.put("premiumpayingterm",ppterm );
                    termData.put("sumassured",suma );
                    termData.put("dateofcommencement",dateofcommencement );
                    termData.put("premiumpaymentupto",premiumpaymentupto );
                    termData.put("approxmaturityamount",approxmaturityamount );
                    termData.put("maturitydate",maturitydate );
                    termData.put("paymentmode",paymentmode );
                    termData.put("bankdetails",ABOUTBANK);

                    SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
                    String num = pre.getString("num", "data no get yet");
                    if (userType.equals("parent")){
                        termData.put("User_name",User_name);
                        termData.put("title","term_policy");
                        termData.put("select_user",num);
                        termData.put("User_index",-5);
                        AddPolicy(num,termData,ID);
                    }else if (userType.equals("child")){
                        termData.put("User_name",User_name);
                        termData.put("title","term_policy");
                        termData.put("select_user",num);
                        termData.put("User_index",index);
                        AddPolicy(num,termData,ID);
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
    private void AddPolicy(String userId, Map<String, Object> carData,int ID) {
        db.collection("USER_ADDED").document(String.valueOf(ID)).set(carData)
                .addOnSuccessListener(aVoid -> {
                    Log.d("TAG", "Car data added successfully!");
                    clearEditTextFields();
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
        four.setText("");
        five.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");
        ten.setText("");
        eleven.setText("");
        twelve.setText("");
        thirteen.setText("");
        fourteen.setText("");
        fifteen.setText("");
        sixteen.setText("");
        eighteen.setText("");
    }
}
