package com.example.dell.jaimaaapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.jaimaaapp.R;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getIntent().getExtras() != null){
            for(String key:getIntent().getExtras().keySet()){
                if(key.equals("click")){
                    Intent intent = new Intent(this,MeetingsActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }

        final Intent mainIntent = new Intent(this, MainActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    startActivity(mainIntent);
                    finish();
                }
            }
        };
        timer.start();
    }
}
