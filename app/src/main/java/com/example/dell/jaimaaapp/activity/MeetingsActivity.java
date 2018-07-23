package com.example.dell.jaimaaapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.dell.jaimaaapp.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MeetingsActivity extends AppCompatActivity {

    private TextView dateText;
    private TextView lastDateText;
    private FirebaseFirestore firebaseFirestore;

    private static final String TAG = "MeetingsLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings);

        Toolbar toolbar = findViewById(R.id.meetings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Meeting Schedules");

        firebaseFirestore = FirebaseFirestore.getInstance();

        dateText = findViewById(R.id.text_meeting);
        lastDateText = findViewById(R.id.last_date_text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseFirestore.collection("nextmeeting").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if(e!=null){
                    Log.d(TAG,e.getMessage());
                }
                for(DocumentChange doc:documentSnapshots.getDocumentChanges()){
                    if(doc.getType() == DocumentChange.Type.ADDED){
                        String date = doc.getDocument().getString("date");
                        String lastDate = doc.getDocument().getString("lastdate");
                        dateText.setText(date);
                        lastDateText.setText(lastDate);
                    }
                }
            }
        });
    }
}
