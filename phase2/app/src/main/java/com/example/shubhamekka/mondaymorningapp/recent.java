package com.example.shubhamekka.mondaymorningapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class recent extends AppCompatActivity{

    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;

    userlocalstore Userlocalstore;

    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.navigation_header_container);
        SetupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabMode);
        tabLayout.setupWithViewPager(mViewPager);

        Userlocalstore = new userlocalstore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(authenticate()==true)
        {
            displayUserInfo();
        }
    }

    private boolean authenticate(){
        return userlocalstore.getUserLoggedIn();

    }

    private void displayUserInfo(){
        user User = userlocalstore.getLogIn();
        username.setText(User.username);
        }

    public void SetupViewPager(ViewPager viewpager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addfragment(new recenttab(), "tab1");
        adapter.addfragment(new categorytab(), "tab2");
        viewpager.setAdapter(adapter);
    }
}