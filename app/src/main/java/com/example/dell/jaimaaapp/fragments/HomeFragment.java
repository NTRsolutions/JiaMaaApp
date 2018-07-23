package com.example.dell.jaimaaapp.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eftimoff.viewpagertransformers.AccordionTransformer;
import com.example.dell.jaimaaapp.R;
import com.example.dell.jaimaaapp.activity.NewActivity;
import com.example.dell.jaimaaapp.adapters.ViewPagerAdapter;
import com.example.dell.jaimaaapp.activity.JoinUsActivity;
import com.example.dell.jaimaaapp.activity.FaqActivity;
import com.example.dell.jaimaaapp.activity.FoundersActivity;
import com.example.dell.jaimaaapp.activity.MedicalandFinancialActivity;
import com.example.dell.jaimaaapp.activity.MeetingsActivity;
import com.example.dell.jaimaaapp.activity.SatsangActivity;
import com.example.dell.jaimaaapp.activity.ScholarshipsActivity;
import com.example.dell.jaimaaapp.activity.TrusteesActivity;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*********************SWITCHING BETWEEN LAYOUTS *************************************/
        LinearLayout layoutSatsang = view.findViewById(R.id.layout_satsang);

        layoutSatsang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SatsangActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout layoutFounders = view.findViewById(R.id.layout_founders);
        layoutFounders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FoundersActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutTrustees = view.findViewById(R.id.layout_trustees);
        layoutTrustees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TrusteesActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutScholorships = view.findViewById(R.id.layout_scholorships);
        layoutScholorships.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScholarshipsActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutFinancialMedical = view.findViewById(R.id.layout_financial_medical);
        layoutFinancialMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MedicalandFinancialActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutfaq = view.findViewById(R.id.layout_faq);
        layoutfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaqActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutjoin = view.findViewById(R.id.layout_join);
        layoutjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), JoinUsActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutnew = view.findViewById(R.id.layout_new);
        layoutnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout layoutMeetings = view.findViewById(R.id.layout_meetings);
        layoutMeetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeetingsActivity.class);
                startActivity(intent);
            }
        });

        /************** SETTING UP THE VIEW PAGER ****************************************/
        viewPager = view.findViewById(R.id.view_pager);

        CircleIndicator indicator = view.findViewById(R.id.indicator);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);
        indicator.setViewPager(viewPager);
        viewPagerAdapter.registerDataSetObserver(indicator.getDataSetObserver());
        viewPager.setPageTransformer(true, new AccordionTransformer());

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTimerTask(), 3000, 5000);


    }

    public class myTimerTask extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() == 0) {
                            viewPager.setCurrentItem(1);

                        } else if (viewPager.getCurrentItem() == 1) {
                            viewPager.setCurrentItem(2);

                        } else if (viewPager.getCurrentItem() == 2) {
                            viewPager.setCurrentItem(3);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }

        }

