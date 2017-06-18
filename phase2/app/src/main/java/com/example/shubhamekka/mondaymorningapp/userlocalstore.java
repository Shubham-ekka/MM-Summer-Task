package com.example.shubhamekka.mondaymorningapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by shubham ekka on 18-Jun-17.
 */

public class userlocalstore {
    public static final String SP_NAME="userdetails";
    static SharedPreferences userLocalDatabase;

    public  userlocalstore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME , 0);
    }

    public static void storeuserdata(user User){
        SharedPreferences.Editor spEditor  = userLocalDatabase.edit();
        spEditor.putString("username" , User.username);
        spEditor.putString("email" , User.email);
        spEditor.putString("password" , User.password);
        spEditor.commit();
    }

    public static user getLogIn(){
        String username = userLocalDatabase.getString("usernamw","");
        String email = userLocalDatabase.getString("email","");
        String password = userLocalDatabase.getString("password","");

        user storeduser = new user(username , email , password);
        return storeduser;
    }

    public static void setUserLoggedIn(boolean LoggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn",LoggedIn);
        spEditor.commit();
    }

    public static boolean getUserLoggedIn(){

        if(userLocalDatabase.getBoolean("LoggedIn",false)==true){
            return  true;
        }
        else{
            return false;
        }
    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }


}
