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

public class CustomSpinner4 {
    private Spinner spinner;
    private String[] items;
    private String selectedValue;
    private EditText AMA; //Approx Maturity Amount



    public CustomSpinner4(Spinner spinner, String[] items,EditText AMA) {
        this.spinner = spinner;
        this.items = items;
        this.AMA = AMA;
        setupSpinner();
    }


    private void setupSpinner() {
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
                if (selectedValue.equals("Bonus")) {
                    AMA.setClickable(true);
                    AMA.setText("");
                    AMA.setFocusable(true);
                    AMA.setFocusableInTouchMode(true);
                } else if (selectedValue.equals("Term")) {
                    AMA.setClickable(false);
                    AMA.setText("0");
                    AMA.setFocusable(false);
                    AMA.setFocusableInTouchMode(false);
                } else if (selectedValue.equals("ULIP")) {
                    AMA.setClickable(false);
                    AMA.setText("DEPENDS ON NAV");
                    AMA.setFocusable(false);
                    AMA.setFocusableInTouchMode(false);
                } else if (selectedValue.equals("Money back")) {
                    AMA.setClickable(true);
                    AMA.setText("");
                    AMA.setFocusable(true);
                    AMA.setFocusableInTouchMode(true);
                } else if (selectedValue.equals("Guaranteed")) {
                    AMA.setClickable(true);
                    AMA.setText("");
                    AMA.setFocusable(true);
                    AMA.setFocusableInTouchMode(true);
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
