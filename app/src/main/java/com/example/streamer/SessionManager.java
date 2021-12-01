package com.example.streamer;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    //Initialize variable
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    //Create constructor
    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences("AppKey",0);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //Create set login method
    public void setLogin(boolean login){
        editor.putBoolean("KEY_LOGIN",login);
        editor.commit();
    }

    //Create get login method
    public boolean getLogin(){
        return sharedPreferences.getBoolean("KEY_LOGIN",false);
    }

    //Create set username method
    public void setUsername(String username)
    {
        editor.putString("KEY_USERNAME",username);
        editor.commit();
    }

    //Create get username method
    public String getUsername(String uname)
    {
        return sharedPreferences.getString("KEY_USERNAME","");
    }
}
