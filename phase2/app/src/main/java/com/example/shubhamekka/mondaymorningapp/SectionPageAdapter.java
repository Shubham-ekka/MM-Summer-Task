package com.example.shubhamekka.mondaymorningapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham ekka on 01-Jun-17.
 */
class SectionsPageAdapter extends FragmentPagerAdapter{
    private static final String TAG = "SectionsPageAdapter";
    private final List<Fragment> mlist = new ArrayList<>();
    private final List<String> mtitle = new ArrayList<>();

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtitle.get(position);
    }
    public void addFragment(Fragment fragment , String title){
        mlist.add(fragment);
        mtitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }
}
