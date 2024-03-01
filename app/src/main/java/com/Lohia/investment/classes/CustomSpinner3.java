package com.Lohia.investment.classes;

import static java.security.AccessController.getContext;

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

public class CustomSpinner3 {
    private final Spinner spinner;
    private final String[] items;
    private String selectedValue;
    private final TextView FUP;
    private final EditText PPT;
    private final TextView Title_PPU; // First Unpaid Premium,Premium Paying Term,Premium Payment Upto
    private final TextView PPU; // First Unpaid Premium,Premium Paying Term

    public CustomSpinner3(Spinner spinner, String[] items,TextView FUP,EditText PPT,TextView Title_PPU,TextView PPU) {
        this.spinner = spinner;
        this.items = items;
        this.FUP = FUP;
        this.PPT = PPT;
        this.Title_PPU = Title_PPU;
        this.PPU = PPU;
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
                if (selectedValue.equals("Single")) {
                    FUP.setText("ALL PREMIUMS PAID");
                    PPT.setText("1");
                    Title_PPU.setVisibility(View.GONE);
                    PPU.setVisibility(View.GONE);
                    PPT.setClickable(false);
                    FUP.setClickable(false);
                    FUP.setOnClickListener(null);
                    PPT.setFocusable(false);
                    PPT.setFocusableInTouchMode(false);
                } else if (selectedValue.equals("Monthly")) {
                    FUP.setText("");
                    PPT.setText("");
                    FUP.setClickable(true);
                    PPT.setClickable(true);
                    PPT.setFocusable(true);
                    FUP.setOnClickListener(new View.OnClickListener() {
                        @Override
                            public void onClick(View view) {
                                Form.showDatePicker(FUP, FUP.getContext());
                            }
                    });
                    PPT.setFocusableInTouchMode(true);
                    Title_PPU.setVisibility(View.VISIBLE);
                    PPU.setVisibility(View.VISIBLE);
                } else if (selectedValue.equals("Quarterly")) {
                    FUP.setClickable(true);
                    PPT.setClickable(true);
                    PPT.setFocusable(true);
                    PPT.setFocusableInTouchMode(true);
                    Title_PPU.setVisibility(View.VISIBLE);
                    PPU.setVisibility(View.VISIBLE);
                    FUP.setText("");
                    PPT.setText("");
                    FUP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Form.showDatePicker(FUP, FUP.getContext());
                        }
                    });
                } else if (selectedValue.equals("Half-Yearly")) {
                    Title_PPU.setVisibility(View.VISIBLE);
                    PPU.setVisibility(View.VISIBLE);
                    FUP.setClickable(true);
                    PPT.setFocusable(true);
                    PPT.setFocusableInTouchMode(true);
                    PPT.setClickable(true);
                    FUP.setText("");
                    PPT.setText("");
                    FUP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Form.showDatePicker(FUP, FUP.getContext());
                        }
                    });
                } else if (selectedValue.equals("Annually")) {
                    Title_PPU.setVisibility(View.VISIBLE);
                    PPU.setVisibility(View.VISIBLE);
                    FUP.setClickable(true);
                    PPT.setClickable(true);
                    PPT.setFocusable(true);
                    PPT.setFocusableInTouchMode(true);
                    FUP.setText("");
                    PPT.setText("");
                    FUP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Form.showDatePicker(FUP, FUP.getContext());
                        }
                    });
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
