package com.example.shubhamekka.mondaymorningapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shubham ekka on 11-Jun-17.
 */
public class SectionPageAdapter extends FragmentPagerAdapter{

    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitle = new ArrayList<>();


    @Override
    public CharSequence getPageTitle(int position) {
        return mfragmentTitle.get(position);
    }

    public void addfragment(Fragment fragment , String string){
        mfragmentList.add(fragment);
        mfragmentTitle.add(string);
    }

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }

}
