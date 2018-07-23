package com.example.dell.jaimaaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dell.jaimaaapp.R;

public class ScholarshipsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarships);

        Toolbar toolbar = findViewById(R.id.scholarships_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Scholarships");

        Button scholarshipButton = findViewById(R.id.scholarships_button);
        scholarshipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScholarshipsActivity.this,ScholarshipAwardedActivity.class);
                startActivity(intent);
            }
        });

    }
}
