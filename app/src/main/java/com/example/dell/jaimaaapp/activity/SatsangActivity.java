package com.example.dell.jaimaaapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.dell.jaimaaapp.R;

public class SatsangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satsang);

        Toolbar toolbar = findViewById(R.id.satsang_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button fulltextButton = findViewById(R.id.read_full_button);
        fulltextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SatsangActivity.this,FullTextActivity.class);
                startActivity(intent);
            }
        });
    }
}
