package com.example.dell.jaimaaapp.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.modal.YearList;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ScholarshipAwardedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<YearList> listData;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "FireLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship_awarded);


        Toolbar toolbar = findViewById(R.id.decisions_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Scholarships Awarded");

        firebaseFirestore = FirebaseFirestore.getInstance();

        listData = new ArrayList<>();
        myAdapter = new MyAdapter(listData);

        recyclerView = findViewById(R.id.decisions_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        listData.clear();
       firebaseFirestore.collection("decisions").orderBy("position", Query.Direction.ASCENDING).addSnapshotListener(this,new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

               if(e!= null){
                   Log.d(TAG,"Error: " + e.getMessage());
               }


               for(DocumentChange doc:documentSnapshots.getDocumentChanges()){

                   if (doc.getType() == DocumentChange.Type.ADDED){
                           Log.d(TAG,"Data Added: " + doc.getDocument().getData());
                           YearList yearList = doc.getDocument().toObject(YearList.class);
                           listData.add(yearList);
                           myAdapter.notifyDataSetChanged();
                   }

               }

           }
       });

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        List<YearList> listArray;

        public MyAdapter(List<YearList> listArray) {
            this.listArray = listArray;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

            holder.yearText.setText(listArray.get(position).getYear());
            final String session = listArray.get(position).getSession();

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ScholarshipAwardedActivity.this,MeetingResultsActivity.class);
                    intent.putExtra("session",session);
                    startActivity(intent);
                }
            });
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            public TextView yearText;
             View mView;

            public MyViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                yearText = mView.findViewById(R.id.text_view_year);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }

}
