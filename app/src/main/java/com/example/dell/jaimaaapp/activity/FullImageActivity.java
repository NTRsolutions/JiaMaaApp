package com.example.dell.jaimaaapp.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.modal.ImageList;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class FullImageActivity extends AppCompatActivity {
    public ViewPager viewPager;
    private ArrayList<String> images;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);
        Bundle args = getIntent().getBundleExtra("Bundle");
        images = args.getStringArrayList("ArrayList");

        pos = getIntent().getExtras().getInt("position");

        viewPager = findViewById(R.id.full_image_viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);

        viewPager.setCurrentItem(pos,false);
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object){
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            layoutInflater = (LayoutInflater) FullImageActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.custom_layout_two,null);
            PhotoView imageView = view.findViewById(R.id.photo_view);
            Glide.with(FullImageActivity.this)
                    .load(images.get(position))
                    .thumbnail(0.5f)
                    .into(imageView);

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    //Toast.makeText(context,"This is slide 1",Toast.LENGTH_SHORT).show();
                }else if(position == 1){
                    //Toast.makeText(context,"This is slide 2",Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(context,"This is slide 3",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

            ViewPager vp = (ViewPager) container;
            vp.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ViewPager vp = (ViewPager)container;
            View view = (View) object;
            vp.removeView(view);
        }
    }
}
