package com.Lohia.investment.classes;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.R;

public class CustomSpinner2 {
    private Spinner spinner;
    private EditText TTD_EDITTEXT;
    private TextView sum_chanage,TTD_Change;
    private String[] items;
    private String selectedValue;
    private boolean SELECTED_VALUR_CHECK;
    public CustomSpinner2(Spinner spinner,TextView sum_chanage,TextView TTD_Change,EditText TTD_EDITTEXT, String[] items) {
        this.spinner = spinner;
        this.sum_chanage = sum_chanage;
        this.TTD_EDITTEXT = TTD_EDITTEXT;
        this.TTD_Change = TTD_Change;
        this.items = items;
        setupSpinner();
    }
    private void setupSpinner() {
        // Create a custom adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(spinner.getContext(),
                R.layout.my_spinner, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER); // Center text in the Spinner
                return v;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                ((TextView) v).setGravity(Gravity.CENTER); // Center text in the dropdown
                return v;
            }
        };

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedValue = items[position];
                if (selectedValue.equals("Personal Accident")) {
                    sum_chanage.setText("Death Sum Assured");
                    TTD_Change.setVisibility(View.VISIBLE);
                    TTD_EDITTEXT.setVisibility(View.VISIBLE);
                    SELECTED_VALUR_CHECK = true;
                    TTD_EDITTEXT.setHint("");
                } else if (selectedValue.equals("Base")) {
                    sum_chanage.setText("Sum Assured");
                    TTD_Change.setVisibility(View.GONE);
                    TTD_EDITTEXT.setVisibility(View.GONE);
                    SELECTED_VALUR_CHECK = false;

                } else if (selectedValue.equals("Super Topup")) {
                    sum_chanage.setText("Sum Assured");
                    TTD_Change.setText("Deductible");
                    TTD_Change.setVisibility(View.VISIBLE);
                    TTD_EDITTEXT.setVisibility(View.VISIBLE);
                    TTD_EDITTEXT.setHint("");
                    SELECTED_VALUR_CHECK = true;
                } else if (selectedValue.equals("Critical Illness")) {
                    sum_chanage.setText("Sum Assured");
                    TTD_Change.setVisibility(View.GONE);
                    TTD_EDITTEXT.setVisibility(View.GONE);
                    SELECTED_VALUR_CHECK = false;

                } else if (selectedValue.equals("Travel")) {
                    sum_chanage.setText("Sum Assured");
                    TTD_Change.setVisibility(View.GONE);
                    TTD_EDITTEXT.setVisibility(View.GONE);
                    SELECTED_VALUR_CHECK = false;

                } else {
                    // Handle other cases if needed
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(spinner.getContext(), "Please select any option", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String getSelectedValue() {
        return selectedValue;
    }
}





