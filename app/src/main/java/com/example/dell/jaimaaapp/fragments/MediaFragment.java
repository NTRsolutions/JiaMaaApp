package com.example.dell.jaimaaapp.fragments;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.dell.jaimaaapp.activity.FullImageActivity;
import com.example.dell.jaimaaapp.modal.ImageList;
import com.example.dell.jaimaaapp.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<ImageList> listData;
    private ArrayList<String> urllist;
    private FirebaseFirestore firebaseFirestore;
    private static final String TAG = "ImageLog";


    public MediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_media, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listData = new ArrayList<>();
        urllist = new ArrayList<>();
        myAdapter =new MyAdapter(listData);
        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView = view.findViewById(R.id.media_recycler_View);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        retrieveDataFromFirebase();
    }

    public void retrieveDataFromFirebase(){

       firebaseFirestore.collection("images").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

               if(e != null){
                   Log.d(TAG,e.getMessage());
               }

               for(DocumentChange doc : documentSnapshots.getDocumentChanges()){
                   if(doc.getType() == DocumentChange.Type.ADDED){
                       ImageList imageList = doc.getDocument().toObject(ImageList.class);
                       String url = imageList.getDownloadURL();
                       listData.add(imageList);
                       urllist.add(url);
                       myAdapter.notifyDataSetChanged();
                   }
               }

           }
       });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        List<ImageList> listArray;

        public MyAdapter(List<ImageList> listArray) {
            this.listArray = listArray;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_image_view,parent,false);

            return new MyViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder,int position) {

            final int p = position;
            String url = listArray.get(position).getDownloadURL();
            holder.setImage(url);
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), FullImageActivity.class);
                    Bundle args = new Bundle();
                    args.putStringArrayList("ArrayList",urllist);
                    intent.putExtra("Bundle",args);
                    intent.putExtra("position",p);
                    startActivity(intent);
                }
            });
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            public View view;
            public ImageView imageView;
            public ProgressBar progressBar;

            public MyViewHolder(View itemView) {
                super(itemView);
                view = itemView;

                imageView = view.findViewById(R.id.gallery_image);
        progressBar = view.findViewById(R.id.media_progress);
            }
            private void setImage(String url){
                Glide.with(getActivity())
                        .load(url)
                        .apply(new RequestOptions().placeholder(R.drawable.image_holder).centerCrop())
                        .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
               .thumbnail(0.5f)
               .into(imageView);
            }

        }

        @Override
        public int getItemCount() {
            return listArray.size();
        }
    }
}
