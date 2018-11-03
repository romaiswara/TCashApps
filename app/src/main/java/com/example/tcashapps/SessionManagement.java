package com.example.tcashapps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    @SuppressLint("CommitPrefEdits")
    public SessionManagement(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences("TCash", 0);
        editor = preferences.edit();
    }

    public void setToken(String token){
        editor.putString(Constant.TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return preferences.getString(Constant.TOKEN, "");
    }
}
