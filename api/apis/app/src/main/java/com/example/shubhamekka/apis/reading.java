package com.example.shubhamekka.apis;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class ArticleRead extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    int size;
    String imageUrls[];
    int post_ids[];
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_article_read);
        mPager = (ViewPager) findViewById(R.id.pager);
        Bundle bundle=getIntent().getExtras();
        size=bundle.getInt("size");
        position=bundle.getInt("position");
        imageUrls=new String[size];
        post_ids=new int[size];
        imageUrls[position]=bundle.getStringArray("imageUrls")[position];
        post_ids[position]=bundle.getIntArray("post_ids")[position];
        Log.i("posts",imageUrls[position]);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {
            Log.i("Compare",String.valueOf(position)+" "+String.valueOf(index));
            return article.create(index,post_ids[position],imageUrls[position]);
        }

        @Override
        public int getCount() {
            return size;
        }
    }

}

