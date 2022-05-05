package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionVpAdapter;
import com.laojiu.app.base.BaseActivity;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.view.TitleView;




public class CommentQuestionListActivity extends BaseActivity {


    private CommentQuestionVpAdapter mAdapter;
    private TitleView mTitle;
    public static void gotoActivity(Context context, CommentQuestionModeBean bean) {
        Intent intent = new Intent(context, CommentQuestionListActivity.class);
        intent.putExtra("bean",  bean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question_list);
        CommentQuestionModeBean mBean = (CommentQuestionModeBean) getIntent().getSerializableExtra("bean");
        mTitle = findViewById(R.id.comment_question_list_title);
        TabLayout tl = findViewById(R.id.comment_question_list_tl);
        mTitle.mTitleTv.setText(mBean.title);
        ViewPager mVp = findViewById(R.id.comment_question_list_vp);
        mAdapter = new CommentQuestionVpAdapter(getSupportFragmentManager(),mBean);
        mVp.setAdapter(mAdapter);
        tl.setupWithViewPager(mVp);
    }



}
