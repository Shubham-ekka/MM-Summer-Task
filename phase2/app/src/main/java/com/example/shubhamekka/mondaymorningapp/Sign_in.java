package com.example.shubhamekka.mondaymorningapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;

public class Sign_in extends AppCompatActivity {
    Button btnskip,btnlogin,btnsignup;
    EditText username,password;

    public void Skip(View view){
        btnskip = (Button) findViewById(R.id.btnskip);
        System.out.println("skip button tapped");

        Intent sk = new Intent(this , recent.class);
        startActivity(sk);

    }
    public void Login(View view){
        btnlogin = (Button) findViewById(R.id.btnlogin);
        System.out.println("login btn tapped");
    }
    public void Signup(View view){
        btnsignup = (Button) findViewById(R.id.btnsignup);
        System.out.println("sign up button tapped");

        Intent up = new Intent(this , sign_up.class);
        startActivity(up);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageView logo = (ImageView) findViewById(R.id.logo);

        logo.animate().alpha(1f);
    }
}
