package com.Lohia.investment.classes;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Lohia.investment.R;

public class customSpinnerBANK {
    private Spinner spinner;
    private String[] items;
    private String selectedValue;
    private LinearLayout layout;
    public customSpinnerBANK(Spinner spinner, String[] items, LinearLayout layout) {
        this.spinner = spinner;
        this.items = items;
        this.layout = layout;
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
                if(selectedValue.equals("ECS")) {
                    layout.setVisibility(View.VISIBLE);
                } else if (selectedValue.equals("Cash/Cheque")) {
                    layout.setVisibility(View.GONE);
                }else{
                    Toast.makeText(spinner.getContext(), "Nothing Selected Please select", Toast.LENGTH_SHORT).show();
                    layout.setVisibility(View.GONE);
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
