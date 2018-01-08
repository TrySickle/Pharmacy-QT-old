package com.nocompany.jason.pharmacyqt;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
                R.layout.spinner_item, totalList);
        totalSpinner.setAdapter(totalAdapter);
        totalSpinner.setOnItemSelectedListener(this);

        amountSpinner = (Spinner) findViewById(R.id.convert_amount_spinner);
        List<String> amountList = new ArrayList<>();
        amountList.add("tsp");
        amountList.add("ml");
        ArrayAdapter<String> amountAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, amountList);
        amountSpinner.setAdapter(amountAdapter);
        amountSpinner.setOnItemSelectedListener(this);

        timesSpinner = (Spinner) findViewById(R.id.convert_times_spinner);
        List<String> timesList = new ArrayList<>();
        timesList.add("\u00D7/Day");
        timesList.add("hours");
        ArrayAdapter<String> timesAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, timesList);
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
        // fixing the next button
        mg.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                ml.requestFocus();
                return true;
            }
        });
        mg2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                ml2.requestFocus();
                return true;
            }
        });
        // spinners listeners
        amountSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateClick();
                convertClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        timesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateClick();
                convertClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        totalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                calculateClick();
                convertClick();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        // edittext listeners
        mg.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculateClick();
                convertClick();
            }
        });
        ml.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculateClick();
                convertClick();
            }
        });
        amount.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculateClick();
                convertClick();
            }
        });
        times.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculateClick();
                convertClick();
            }
        });
        total.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                calculateClick();
                convertClick();
            }
        });
        mg2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                convertClick();
            }
        });
        ml2.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                convertClick();
            }
        });
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

    public void calculateClick() {
        TextView totalInMl = (TextView) findViewById(R.id.convert_total_ml);
        if (totalSpinner.getSelectedItem().toString().equals("days")) {
            try {
                totalInMl.setText(String.format(Locale.US, "%s ml", calculateTotal(getAmount(), getTotal()).toString()));
            } catch (NumberFormatException e) {
                totalInMl.setText("");
            }
        } else {
            try {
                totalInMl.setText(String.format(Locale.US, "%s ml", total.getText().toString()));
            } catch (NumberFormatException e) {
                totalInMl.setText("");
            }
        }

    }

    private BigDecimal calculateTotal(BigDecimal amountInMl, BigDecimal days) {
        BigDecimal i_timesPerDay = getTimes();
        return amountInMl.multiply(i_timesPerDay).multiply(days, MathContext.DECIMAL32);
    }

    public void convertClick() {
        try {
            BigDecimal i_timesPerDay = getTimes();
            BigDecimal i_total = getTotal();

            BigDecimal dosage = getDosage(getMg(), getMl(), getAmount());
            BigDecimal newAmount = getNewAmount(dosage, getMg2(), getMl2());

            BigDecimal totalInDays;
            if (totalSpinner.getSelectedItem().toString().equals("ml")) {
                totalInDays = i_total.divide(getAmount().multiply(i_timesPerDay), MathContext.DECIMAL32);
            } else {
                totalInDays = i_total;
            }

            // Take newAmount either times per day or every x hours, for totalInDays, new amount prescribed

            if (timesSpinner.getSelectedItem().toString().equals("hours")) {
//                if (amountSpinner.getSelectedItem().toString().equals("tsp")) {
//                    converted.setText(String.format(Locale.US, "Take %s tsp every %d hours for %s days, %s ml total",
//                            newAmount.divide(new BigDecimal(5), MathContext.DECIMAL32).toString(), 24 / i_timesPerDay.intValue(),
//                            totalInDays.toString(), calculateTotal(newAmount, totalInDays).toString()));
//                } else {
                    converted.setText(String.format(Locale.US, "Take %s ml every %d hours for %s days, %s ml total",
                            newAmount.toString(), 24 / i_timesPerDay.intValue(),
                            totalInDays.toString(), calculateTotal(newAmount, totalInDays).toString()));
            } else {
//                if (amountSpinner.getSelectedItem().toString().equals("tsp")) {
//                    converted.setText(String.format(Locale.US, "Take %s tsp %s times per day for %s days, %s ml total",
//                            newAmount.divide(new BigDecimal(5), MathContext.DECIMAL32).toString(), i_timesPerDay.toString(),
//                            totalInDays.toString(), calculateTotal(newAmount, totalInDays).toString()));
//                } else {
                    converted.setText(String.format(Locale.US, "Take %s ml %s times per day for %s days, %s ml total",
                            newAmount.toString(), i_timesPerDay.toString(),
                            totalInDays.toString(), calculateTotal(newAmount, totalInDays).toString()));
            }

        } catch (NumberFormatException e) {
            converted.setText("");
        }
    }

    private BigDecimal getDosage(BigDecimal mg, BigDecimal ml, BigDecimal amountInMl) {
        return mg.divide(ml, MathContext.DECIMAL32).multiply(amountInMl);
    }

    private BigDecimal getNewAmount(BigDecimal mg, BigDecimal ml, BigDecimal amountInMl, BigDecimal mg2, BigDecimal ml2) {
        BigDecimal dosage = getDosage(mg, ml, amountInMl);
        return getNewAmount(dosage, mg2, ml2);
    }

    private BigDecimal getNewAmount(BigDecimal dosage, BigDecimal mg2, BigDecimal ml2) {
        return dosage.multiply(ml2).divide(mg2, MathContext.DECIMAL32);
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
