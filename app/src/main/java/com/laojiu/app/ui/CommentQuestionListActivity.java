package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionVpAdapter;
import com.laojiu.app.base.BaseActivity;
import com.laojiu.app.bean.EventCommentQuestionBean;
import com.laojiu.app.bean.WelcomeBean;
import com.laojiu.app.view.TitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class CommentQuestionListActivity extends BaseActivity {


    private CommentQuestionVpAdapter mAdapter;
    private TitleView mTitle;

    public static void gotoActivity(Context context) {
        Intent intent = new Intent(context, CommentQuestionListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comment_question_list);
        mTitle = findViewById(R.id.comment_question_list_title);
        ViewPager mVp = findViewById(R.id.comment_question_list_vp);
        mAdapter = new CommentQuestionVpAdapter(getSupportFragmentManager());
        mVp.setAdapter(mAdapter);
        mVp.addOnPageChangeListener(mPageChangeListener);

    }

    ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            CharSequence title = mAdapter.getPageTitle(position);
            mTitle.mTitleTv.setText(title);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
