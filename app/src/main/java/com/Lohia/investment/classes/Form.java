package com.Lohia.investment.classes;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class Form {
    public static String checkEditTextNotEmpty(EditText editText) {
        String text = editText.getText().toString().trim();

        if (text.isEmpty()) {
//            editText.setError("This field is not allowed to be empty");
            // Or set a default value to the EditText if needed:
            editText.setHint("This field is not allowed to be empty");
            return null; // Return null or handle empty case as needed
        } else {
            return text; // Return the value if it's not empty
        }
    }


    public static void showDatePicker(TextView textView, Context context) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    textView.setText(selectedDate);
                }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
}
