package com.Lohia.investment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.Models.BankListGenerator;
import com.Lohia.investment.R;
import com.Lohia.investment.classes.CustomSpinner;
import com.Lohia.investment.classes.Form;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FixedDepositsFragment extends Fragment {

    String FD_from,FD_type,OrganizationBank,OrganizationPB,FDRNo,Frequency_interest,Interest_Rate,Investment_Amount,Maturity_Date,Maturity_Amount,Nominee_Name;
    Spinner one,two,three_spin,four;
    EditText three_edittext,five,six,seven,ten,nine;
    TextView eight;
    CustomSpinner customSpinner1,customSpinner3,customSpinner4;
    Button FD_BTN;
    String[] banksArray = BankListGenerator.getBanks();
    LinearLayout Layout_spinner,Layout_text;
    String[] from = new String[]{" ", "Lohia Investments", "Other"};
    String[] type = new String[]{" ", "Bank FD", "Private FD", "Post Office FD"};
    String[] interest  = new String[]{" ", "Cumulative", "Monthly", "Quarterly", "Half Yearly", "Yearly"};
    String selectedFDType;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixed_deposits, container, false);
        one = view.findViewById(R.id.text_field_one);
        customSpinner1 = new CustomSpinner(one, from);
        two = view.findViewById(R.id.text_field_two);
        three_spin = view.findViewById(R.id.text_field_three_spinner);
        customSpinner3 = new CustomSpinner(three_spin, banksArray);
        four = view.findViewById(R.id.text_field_four);
        customSpinner4 = new CustomSpinner(four, interest);
        Layout_spinner= (LinearLayout) view.findViewById(R.id.three_spinner);
        Layout_text=(LinearLayout) view.findViewById(R.id.three_edit);
        three_edittext=view.findViewById(R.id.text_field_three_edit);
        five=view.findViewById(R.id.text_field_five);
        six=view.findViewById(R.id.text_field_six);
        seven=view.findViewById(R.id.text_field_seven);
        eight=view.findViewById(R.id.text_field_eight);
        nine=view.findViewById(R.id.text_field_nine);
        ten=view.findViewById(R.id.text_field_ten);
        FD_BTN=view.findViewById(R.id.add_FD_BTN);

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Form.showDatePicker(eight,getContext());
            }
        });
        FD_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(five.getText().toString().isEmpty()|| six.getText().toString().isEmpty()|| seven.getText().toString().isEmpty()|| nine.getText().toString().isEmpty()|| ten.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Something went wrong! Please check some fields are empty", Toast.LENGTH_LONG).show();
                }else{
                    FD_from=customSpinner1.getSelectedValue();
                    FD_type= selectedFDType;
                    Frequency_interest=customSpinner4.getSelectedValue();
                    FDRNo=five.getText().toString();
                    Interest_Rate=six.getText().toString();
                    Investment_Amount=seven.getText().toString();
                    Maturity_Date=eight.getText().toString();
                    Maturity_Amount=nine.getText().toString();
                    Nominee_Name=ten.getText().toString();
                    OrganizationBank=customSpinner3.getSelectedValue();
                    OrganizationPB=three_edittext.getText().toString();
                    if (FD_from.isEmpty() ||Frequency_interest.isEmpty() ||FD_type.isEmpty() ||FDRNo.isEmpty() || Interest_Rate.isEmpty() || Investment_Amount.isEmpty() || Maturity_Date.isEmpty() || Maturity_Amount.isEmpty() || Nominee_Name.isEmpty()) {

                        Toast.makeText(getContext(), "Something went wrong! Please check some fields are empty", Toast.LENGTH_LONG).show();

                    }else{
                        SharedPreferences pre = getContext().getSharedPreferences("login", MODE_PRIVATE);
                        String num = pre.getString("num", "data no get yet");
                        Map<String, Object> FDData = new HashMap<>();
                        FDData.put("User",num);
                        FDData.put("FD_from",FD_from);
                        FDData.put("FD_type",FD_type);
                        FDData.put("OrganizationBank",OrganizationBank);
                        FDData.put("OrganizationPB",OrganizationPB);
                        FDData.put("Frequency_interest",Frequency_interest);
                        FDData.put("FDRNo",FDRNo);
                        FDData.put("Interest_Rate",Interest_Rate);
                        FDData.put("Investment_Amount",Investment_Amount);
                        FDData.put("Maturity_Date",Maturity_Date);
                        FDData.put("Maturity_amount",Maturity_Amount);
                        FDData.put("Nominee_Name",Nominee_Name);
                        FDPolicy(num,FDData);
                    }
                }
            }
        });


//        spinner work
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.my_spinner, type);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        two.setAdapter(adapter);
        two.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedFDType = parent.getItemAtPosition(position).toString();

                if (selectedFDType.equals("Bank FD")) {
                    Layout_spinner.setVisibility(View.VISIBLE);
                    Layout_text.setVisibility(View.GONE);
                } else if (selectedFDType.equals("Private FD")) {
                    Layout_text.setVisibility(View.VISIBLE);
                    Layout_spinner.setVisibility(View.GONE);
                } else if (selectedFDType.equals("Post Office FD")) {
                    Layout_text.setVisibility(View.VISIBLE);
                    Layout_spinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
        return view;
    }
    private void FDPolicy(String userId, Map<String, Object> FDData) {
        DocumentReference userRef = db.collection("users").document(userId);
        userRef.update("Fixed_Deposits", FieldValue.arrayUnion(FDData))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "FD Add successfully!", Toast.LENGTH_SHORT).show();
                            clearEditTextFields();
                        } else {
                            Log.w("TAG", "Error updating FD data", task.getException());
                        }
                    }
                });
    }
    private void clearEditTextFields() {
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");
        ten.setText("");
    }
}