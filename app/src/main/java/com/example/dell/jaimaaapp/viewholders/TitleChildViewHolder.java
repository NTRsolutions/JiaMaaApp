package com.example.dell.jaimaaapp.viewholders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.dell.jaimaaapp.R;

public class TitleChildViewHolder extends ChildViewHolder {

    public TextView option1,option2;
    public View view;

    public TitleChildViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
    }
}
