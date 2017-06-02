package com.example.shubhamekka.mondaymorningapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by shubham ekka on 02-Jun-17.
 */

public class recenttab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recenttab, container, false);
        Toast.makeText(getContext(), "recent", Toast.LENGTH_LONG).show();
        return view;
    }
}
