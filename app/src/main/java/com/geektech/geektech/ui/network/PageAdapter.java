package com.geektech.geektech.ui.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.geektech.geektech.R;


public class PageAdapter extends FragmentPagerAdapter {

    private String[] TAB_TITLES;
    private String[] TAB_CONTENT;

    public PageAdapter(Context context, FragmentManager fm) {
        super(fm);
        TAB_TITLES = context.getResources().getStringArray(R.array.tabs_network);
        TAB_CONTENT = context.getResources().getStringArray(R.array.tabs_network_content);
    }

    @Override
    public Fragment getItem(int position) {
        Log.e("ololo", "onViewCreated: " + position + (TAB_CONTENT[position]));
        return PageFragment.newInstance(TAB_CONTENT[position]);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}
