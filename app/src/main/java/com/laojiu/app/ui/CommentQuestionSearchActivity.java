package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionSearchAdapter;
import com.laojiu.app.base.BaseActivity;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.view.TitleView;

/**
 * 搜索
 */
public class CommentQuestionSearchActivity extends BaseActivity {

    private AppCompatEditText mEt;
    private CommentQuestionSearchAdapter mAdapter;


    public static void gotoActivity(Context context, CommentQuestionModeBean bean) {
        Intent intent = new Intent(context, CommentQuestionSearchActivity.class);
        intent.putExtra("bean", bean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question_search);
        CommentQuestionModeBean mBean = (CommentQuestionModeBean) getIntent().getSerializableExtra("bean");
        TitleView title = findViewById(R.id.comment_question_search_title);
        title.mTitleTv.setText(mBean.title);
        mEt = findViewById(R.id.comment_question_search_et);
        findViewById(R.id.comment_question_search_btn).setOnClickListener(v -> save());

        RecyclerView rv = findViewById(R.id.comment_question_search_rv);
        rv.setLayoutManager(new FlexboxLayoutManager(this));
        String mContentTag = AppContent.answerTag;
        if (TextUtils.equals(CommentQuestionModeBean.titleStr, mBean.title))
            mContentTag = AppContent.titleTag;
        mAdapter = new CommentQuestionSearchAdapter(this, mContentTag);
        rv.setAdapter(mAdapter);
    }

    private void save() {

        String str = mEt.getText().toString().trim();
        mEt.setText("");
        mAdapter.addData(str);
    }
}
