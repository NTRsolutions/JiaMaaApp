package com.example.dell.jaimaaapp.activity;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.adapters.MyAdapter;
import com.example.dell.jaimaaapp.modal.TitleChild;
import com.example.dell.jaimaaapp.modal.TitleCreator;
import com.example.dell.jaimaaapp.modal.TitleParent;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((MyAdapter)recyclerView.getAdapter()).onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        Toolbar toolbar = findViewById(R.id.faq_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FAQ's");

        recyclerView = findViewById(R.id.faq_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter myAdapter = new MyAdapter(this,initData());
        myAdapter.setParentClickableViewAnimationDefaultDuration();
        myAdapter.setParentAndIconExpandOnClick(true);

        recyclerView.setAdapter(myAdapter);

    }

    private List<ParentObject> initData() {

        TitleCreator titleCreator = TitleCreator.get(this);
        List<TitleParent> titles = titleCreator.getAll();
        List<ParentObject> parentObject = new ArrayList<>();

        for(TitleParent title: titles){
            List<Object> childList = new ArrayList<>();
            childList.add(new TitleChild("Add To Contacts", "Send Message"));
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;
    }
}
