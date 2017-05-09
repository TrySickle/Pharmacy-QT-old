package com.nocompany.jason.pharmacyqt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void convertClick(View view) {
        startActivity(new Intent(this, ConvertActivity.class));
    }

    public void daysClick(View view) {
        startActivity(new Intent(this, DaysActivity.class));
    }
}
