package com.example.shubhamekka.mondaymorningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by shubham ekka on 10-Jun-17.
 */
public class categorytab extends Fragment {

    Button btndept,btnview,btncareer,btnalumni,btncampus,btndd,btnbuzz;

    public void Buzz(View view){
        btnbuzz = (Button) view.findViewById(R.id.btnbuzz);
        System.out.println("buzz button tapped");

        Intent bz = new Intent(getActivity(), buzz.class);
        startActivity(bz);

    }
    public void VIew(View view){
        btnview = (Button) view.findViewById(R.id.btnview);
        System.out.println("view button tapped");

        Intent vw = new Intent(getActivity(),View.class);
        startActivity(vw);
    }
    public void Dept(View view){
        btndept = (Button) view.findViewById(R.id.btndept);
        System.out.println("dept button tapped");

        Intent dp = new Intent(getActivity(), department.class);
        startActivity(dp);
    }
    public void Career(View view){
        btncareer = (Button) view.findViewById(R.id.btncareer);
        System.out.println("career button tapped");

        Intent cr = new Intent(getActivity() , career.class);
        startActivity(cr);
    }
    public void Campus(View view){
        btncampus = (Button) view.findViewById(R.id.btncampus);
        System.out.println("campus button tapped");

        Intent cp = new Intent(getActivity() , campus.class);
        startActivity(cp);
    }
    public void Alimni(View view){
        btnalumni = (Button) view.findViewById(R.id.btnalumni);
        System.out.println("alumni button tapped");

        Intent al = new Intent(getActivity() , alumni.class);
        startActivity(al);
    }
    public void Dd(View view){
        btndd = (Button) view.findViewById(R.id.btndd);
        System.out.println("dd button tapped");

        Intent dc = new Intent(getActivity() , DdCwc.class);
        startActivity(dc);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categorytab, container, false);
        return view;
    }


}
