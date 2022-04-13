package com.laojiu.app.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laojiu.app.R;
import com.laojiu.app.base.BaseMainActivity;
import com.laojiu.app.view.NoScrollViewPager;

public class MainActivity extends BaseMainActivity {

    private NoScrollViewPager mMainFl;
    private BottomNavigationView mMainBnv;

    public static void gotoActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainFl = findViewById(R.id.main_vp);
        mMainBnv = findViewById(R.id.main_bnv);
        initMainView(mMainFl, mMainBnv);
        mMainBnv.setOnNavigationItemSelectedListener(mItemSelectedListener);

    }

    BottomNavigationView.OnNavigationItemSelectedListener mItemSelectedListener = item -> {
        int itemId = item.getItemId();
        if (itemId == R.id.tab_center) {
            mMainFl.setCurrentItem(0);
        }
        if (itemId == R.id.tab_user) {
            mMainFl.setCurrentItem(1);
        }
        return true;
    };


}