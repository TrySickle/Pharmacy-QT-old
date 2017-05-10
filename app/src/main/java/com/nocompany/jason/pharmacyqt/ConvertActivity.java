package com.nocompany.jason.pharmacyqt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConvertActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner totalSpinner;
    private Spinner amountSpinner;
    private Spinner timesSpinner;
    private EditText mg;
    private EditText ml;
    private EditText amount;
    private EditText times;
    private EditText total;
    private EditText mg2;
    private EditText ml2;
    private TextView converted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        totalSpinner = (Spinner) findViewById(R.id.convert_total_spinner);
        List<String> totalList = new ArrayList<>();
        totalList.add("days");
        totalList.add("ml");
        ArrayAdapter<String> totalAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, totalList);
        totalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        totalSpinner.setAdapter(totalAdapter);
        totalSpinner.setOnItemSelectedListener(this);

        amountSpinner = (Spinner) findViewById(R.id.convert_amount_spinner);
        List<String> amountList = new ArrayList<>();
        amountList.add("tsp");
        amountList.add("ml");
        ArrayAdapter<String> amountAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, amountList);
        amountAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        amountSpinner.setAdapter(amountAdapter);
        amountSpinner.setOnItemSelectedListener(this);

        timesSpinner = (Spinner) findViewById(R.id.convert_times_spinner);
        List<String> timesList = new ArrayList<>();
        timesList.add("\u00D7/Day");
        timesList.add("hours");
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, timesList);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timesSpinner.setAdapter(timesAdapter);
        timesSpinner.setOnItemSelectedListener(this);

        mg = (EditText) findViewById(R.id.convert_mg);
        ml = (EditText) findViewById(R.id.convert_ml);
        amount = (EditText)findViewById(R.id.convert_amount);
        times = (EditText) findViewById(R.id.convert_times);
        total = (EditText) findViewById(R.id.convert_total);
        mg2 = (EditText) findViewById(R.id.convert_mg2);
        ml2 = (EditText) findViewById(R.id.convert_ml2);
        converted = (TextView) findViewById(R.id.convert_final);
//        mg.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) { }
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String item = parent.getItemAtPosition(pos).toString();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void calculateClick(View view) {
        TextView totalInMl = (TextView) findViewById(R.id.convert_total_ml);
        if (totalSpinner.getSelectedItem().toString().equals("days")) {
            try {
                BigDecimal i_mg = getMg();
                BigDecimal i_ml = getMl();
                BigDecimal i_amountInMl = getAmount();
                BigDecimal i_timesPerDay = getTimes();
                BigDecimal i_total = getTotal();

                BigDecimal dosage = i_mg.divide(i_ml, MathContext.DECIMAL32).multiply(i_amountInMl);
                totalInMl.setText(String.format(Locale.US, "%s ml", dosage.multiply(i_timesPerDay).multiply(i_total).toString()));

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Must enter all information", Toast.LENGTH_SHORT).show();
            }
        } else {
            totalInMl.setText("");
        }
    }

    public void convertClick(View view) {
        try {
            BigDecimal i_mg = getMg();
            BigDecimal i_ml = getMl();
            BigDecimal i_amountInMl = getAmount();
            BigDecimal i_timesPerDay = getTimes();
            BigDecimal i_total = getTotal();
            BigDecimal i_mg2 = getMg2();
            BigDecimal i_ml2 = getMl2();

            BigDecimal dosage = i_mg.divide(i_ml, MathContext.DECIMAL32).multiply(i_amountInMl);
            BigDecimal newDosage = dosage.multiply(i_ml2).divide(i_mg2, MathContext.DECIMAL32);

            BigDecimal totalInDays;
            if (totalSpinner.getSelectedItem().toString().equals("ml")) {
                totalInDays = i_total.divide(dosage.multiply(i_timesPerDay), MathContext.DECIMAL32);
            } else {
                totalInDays = i_total;
            }

            // Take newDosage either times per day or every x hours, for totalInDays, new amount prescribed

            if (timesSpinner.getSelectedItem().toString().equals("hours")) {
                if (amountSpinner.getSelectedItem().toString().equals("tsp")) {
                    converted.setText(String.format(Locale.US, "Take %s tsp every %d hours for %s days", newDosage.divide(new BigDecimal(5), MathContext.DECIMAL32).toString(), 24 / i_timesPerDay.intValue(), totalInDays.toString()));
                } else {
                    converted.setText(String.format(Locale.US, "Take %s ml every %d hours for %s days", newDosage.toString(), 24 / i_timesPerDay.intValue(), totalInDays.toString()));
                }
            } else {
                if (amountSpinner.getSelectedItem().toString().equals("tsp")) {
                    converted.setText(String.format(Locale.US, "Take %s tsp %s times per day for %s days", newDosage.divide(new BigDecimal(5), MathContext.DECIMAL32).toString(), i_timesPerDay.toString(), totalInDays.toString()));
                } else {
                    converted.setText(String.format(Locale.US, "Take %s ml %s times per day for %s days", newDosage.toString(), i_timesPerDay.toString(), totalInDays.toString()));
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Must enter all information", Toast.LENGTH_SHORT).show();
        }

    }

    private BigDecimal getMg() {
        return new BigDecimal(mg.getText().toString());
    }

    private BigDecimal getMl() {
        return new BigDecimal(ml.getText().toString());
    }

    private BigDecimal getAmount() {
        if (amountSpinner.getSelectedItem().toString().equals("tsp")) {
            return new BigDecimal(Integer.parseInt(amount.getText().toString()) * 5);
        } else {
            return new BigDecimal(amount.getText().toString());
        }
    }

    private BigDecimal getTimes() {
        if (timesSpinner.getSelectedItem().toString().equals("hours")) {
            return new BigDecimal(24 / Integer.parseInt(times.getText().toString()));
        }
        return new BigDecimal(times.getText().toString());
    }

    private BigDecimal getTotal() {
        return new BigDecimal(total.getText().toString());
    }

    private BigDecimal getMg2() {
        return new BigDecimal(mg2.getText().toString());
    }

    private BigDecimal getMl2() {
        return new BigDecimal(ml2.getText().toString());
    }
}
