package com.nocompany.jason.pharmacyqt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class ConvertActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner convertSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        convertSpinner = (Spinner) findViewById(R.id.convert_total_spinner);
        List<String> categories = new ArrayList<>();
        categories.add("days");
        categories.add("ml");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        convertSpinner.setAdapter(adapter);
        convertSpinner.setOnItemSelectedListener(this);
        int spinnerPosition = adapter.getPosition("days");
        convertSpinner.setSelection(spinnerPosition);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        String item = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        //convertSpinner.setSelection(0);
    }
}
