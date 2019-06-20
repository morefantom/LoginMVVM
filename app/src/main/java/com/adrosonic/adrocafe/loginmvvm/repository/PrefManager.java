package com.adrosonic.adrocafe.loginmvvm.repository;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }

    public void saveLogin(Boolean isLogin){
        SharedPreferences sharedPreferences = context.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login", isLogin);
        editor.apply();
    }

    public boolean isUserLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);
    }
}
