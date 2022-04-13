package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.laojiu.app.R;
import com.laojiu.app.adapter.QuestionViewPagerAdapter;
import com.laojiu.app.base.BaseActivity;

/**
 * 问题主界面
 */
public class QuestionActivity extends BaseActivity {

    public static void gotoActivity(Context context) {
        Intent intent = new Intent(context, QuestionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ViewPager vp = findViewById(R.id.question_vp);
        QuestionViewPagerAdapter adapter = new QuestionViewPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        vp.addOnPageChangeListener(mOnPageChangeListener);
    }
    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
