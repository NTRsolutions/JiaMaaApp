package com.example.dell.jaimaaapp.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.dell.jaimaaapp.R;


public class TitleParentViewHolder extends ParentViewHolder{

    public TextView textView;
    public ImageButton imageButton;
    public View view;

    public TitleParentViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        textView = view.findViewById(R.id.text_faq);
        imageButton = view.findViewById(R.id.image_button_faq);
    }
}
