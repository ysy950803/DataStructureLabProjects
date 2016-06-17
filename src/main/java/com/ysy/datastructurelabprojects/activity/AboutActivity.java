package com.ysy.datastructurelabprojects.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ysy.datastructurelabprojects.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupActionBar();

    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //覆盖整个Activity的返回按钮
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed(); //调用onKeyDown内部方法
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
