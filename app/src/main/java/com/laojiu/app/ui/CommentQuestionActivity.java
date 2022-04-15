package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionModeAdapter;
import com.laojiu.app.base.BaseActivity;

public class CommentQuestionActivity extends BaseActivity {


    private CommentQuestionModeAdapter mAdapter;

    public static void gotoActivity(Context context) {
        Intent intent = new Intent(context, CommentQuestionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question_mode);


        RecyclerView rv = findViewById(R.id.comment_question_mode_rv);
        mAdapter = new CommentQuestionModeAdapter(this);
        rv.setLayoutManager(new GridLayoutManager(this, 4));
        rv.setAdapter(mAdapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter.notifyDataSetChanged();
    }
}
