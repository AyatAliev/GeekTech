package com.geektech.geektech.оnBoard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.geektech.geektech.MainActivity;
import com.geektech.geektech.preferenceHelper.PreferenceHelper;
import com.geektech.geektech.R;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;


public class OnBoardActivity extends AppCompatActivity {

    public String POS = "pos";
    private ViewPager viewPager;
    private TextView SkipButton, NextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        viewPager = findViewById(R.id.view_Pager);
        SkipButton = findViewById(R.id.skip);
        NextButton = findViewById(R.id.next);

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);
        wormDotsIndicator.setViewPager(viewPager);


        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNextClick();
            }
        });

        SkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceHelper.getInstance(OnBoardActivity.this).setIsShow();
                startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
                finish();
            }
        });


        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == SectionPagerAdapter.PAGES_COUNT - 1) {
                    NextButton.setText(R.string.start);
                    SkipButton.setVisibility(View.GONE);
                } else {
                    NextButton.setText(R.string.next);
                    SkipButton.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void onNextClick() {
        if (viewPager.getCurrentItem() < SectionPagerAdapter.PAGES_COUNT - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            PreferenceHelper.getInstance(OnBoardActivity.this).setIsShow();
            startActivity(new Intent(OnBoardActivity.this, MainActivity.class));
            finish();
        }
    }


    public class SectionPagerAdapter extends FragmentPagerAdapter {
        public static final int PAGES_COUNT = 3;

        public SectionPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            OnBoardFragment fragment = new OnBoardFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(POS, position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }
}
