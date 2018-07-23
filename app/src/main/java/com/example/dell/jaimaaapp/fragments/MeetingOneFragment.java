package com.example.dell.jaimaaapp.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.modal.Results;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingOneFragment extends Fragment {
    private String session;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "DecisionLog";

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Results> listData;



    public MeetingOneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_meeting_one, container, false);

        if(getArguments() != null) {
            session = getArguments().getString("session");
        }
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.results_recycler_view);
        listData = new ArrayList<>();
        myAdapter = new MyAdapter(listData);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();

            firebaseFirestore.collection(session).whereEqualTo("meeting", "1").addSnapshotListener(getActivity(), new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                    if (e != null) {
                        Log.d(TAG, "Error : " + e.getMessage());
                    }

                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            Log.d(TAG, "Data Added: " + doc.getDocument().getData());
                            Results results = doc.getDocument().toObject(Results.class);
                            listData.add(results);
                            myAdapter.notifyDataSetChanged();
                        }

                    }

                }
            });


    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        List<Results> listArray;

        public MyAdapter(List<Results> listArray) {
            this.listArray = listArray;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_result_view,parent,false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            holder.nameText.setText(listArray.get(position).getName());
            holder.fathernameText.setText(listArray.get(position).getFathername());
            holder.ageText.setText(listArray.get(position).getAge());
            holder.chequeText.setText(listArray.get(position).getCheque());
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            View mView;
            TextView nameText;
            TextView ageText;
            TextView chequeText;
            TextView fathernameText;

            public MyViewHolder(View itemView) {
                super(itemView);
                mView = itemView;

                nameText = mView.findViewById(R.id.tv_name);
                ageText = mView.findViewById(R.id.tv_age);
                chequeText = mView.findViewById(R.id.tv_cheque);
                fathernameText = mView.findViewById(R.id.tv_fathers_name);
            }
        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }


}
