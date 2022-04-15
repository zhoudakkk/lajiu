package com.laojiu.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionDetailsAdapter;
import com.laojiu.app.base.BaseActivity;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.EventCommentQuestionBean;
import com.laojiu.app.utils.DataUtil;
import com.laojiu.app.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * 问题主界面
 */
public class CommentQuestionDetailsActivity extends BaseActivity {


    private DaoThemeBean mData;

    private Map<String, String> titleMap = new HashMap<>();
    private CommentQuestionDetailsAdapter mAdapter;

    public static void gotoActivity(Context context, long appID, boolean isContinue) {
        Intent intent = new Intent(context, CommentQuestionDetailsActivity.class);
        intent.putExtra("appID", appID);
        intent.putExtra("isContinue", isContinue);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_question_details);

        long appID = getIntent().getLongExtra("appID", 0);
        boolean isContinue = getIntent().getBooleanExtra("isContinue", false);
        if (isContinue) {
            mData = DataUtil.getDataByAppID(appID);
        } else {
            mData = DataUtil.getClearAnswerByAppID(appID);
        }

        if (mData == null) {
            Utils.showToast("数据异常");
            finish();
            return;
        }
        titleMap.put(AppContent.QuestionType, "概括问题");
        titleMap.put(AppContent.ReasonType, "概括原因");
        titleMap.put(AppContent.MethodType, "概括对策");

        initView();
    }

    private void initView() {

        AppCompatTextView titleTv = findViewById(R.id.comment_question_details_title_tv);
        titleTv.setText(titleMap.get(mData.type));
        AppCompatTextView themeTv = findViewById(R.id.comment_question_details_theme_tv);
        themeTv.setText(mData.theme);


        RecyclerView rv = findViewById(R.id.comment_question_details_rv);
        rv.setLayoutManager(new GridLayoutManager(CommentQuestionDetailsActivity.this, 1));
        mAdapter = new CommentQuestionDetailsAdapter(CommentQuestionDetailsActivity.this, mData);
        rv.setAdapter(mAdapter);


        findViewById(R.id.comment_question_details_clear_all_history).setOnClickListener(v -> clearHistoryAnswer());
        findViewById(R.id.comment_question_details_up_all).setOnClickListener(v -> setAnswer());
    }

    private void clearHistoryAnswer() {
        mData = DataUtil.clearHistoryAnswer(mData);
        mAdapter.setData(mData);
    }

    private void setAnswer() {
        mData = DataUtil.setAnswer(mData);
        mAdapter.setData(mData);
        Utils.showToast("提交完成");
        EventBus.getDefault().postSticky(new EventCommentQuestionBean(mData.appID));
    }
}
