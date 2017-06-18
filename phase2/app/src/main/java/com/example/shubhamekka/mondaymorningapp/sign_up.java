package com.example.shubhamekka.mondaymorningapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class sign_up extends AppCompatActivity {
    public Button btnsignup,btnback;
    public EditText username,email,password;

    public  void SignUp(View view){
        btnsignup = (Button) findViewById(R.id.btnsignup);
        System.out.println("Sign up button tapped");

        String Username = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();

        user registered = new user(Username , Email , Password);

    }

    public void Back(View view){
        btnback = (Button) findViewById(R.id.btnback);
        System.out.println("back button tapped");

        Intent b = new Intent(this , Sign_in.class);
        startActivity(b);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.animate().alpha(1f);
    }
}
