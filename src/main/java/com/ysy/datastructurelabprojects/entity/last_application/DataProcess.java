package com.ysy.datastructurelabprojects.entity.last_application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shengyu Yao on 2016/6/14.
 */
public class DataProcess {

    Context context;

    public DataProcess(Context context) {
        this.context = context;
    }

    public void saveData(String key, String data) {
        SharedPreferences sp = this.context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (data == null)
            editor.putString(key, "");
        else
            editor.putString(key, data);
        editor.apply();
    }

    public void saveData(String key, int data) {
        SharedPreferences sp = this.context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, data);
        editor.apply();
    }

    public String readStrData(String key) {
        SharedPreferences sp = this.context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }

    public int readIntData(String key) {
        SharedPreferences sp = this.context.getSharedPreferences(key, Context.MODE_PRIVATE);
        return sp.getInt(key, 0);
    }

}
