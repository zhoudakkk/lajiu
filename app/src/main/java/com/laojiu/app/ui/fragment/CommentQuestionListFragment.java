package com.laojiu.app.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionAdapter;
import com.laojiu.app.base.BaseFragment;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.EventCommentQuestionBean;
import com.laojiu.app.utils.DataUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.List;


public class CommentQuestionListFragment extends BaseFragment {

    private String mTag = AppContent.QuestionType;
    private CommentQuestionAdapter mAdapter;
    private FloatingActionButton btn;

    public static CommentQuestionListFragment getInstance(String tag, CommentQuestionModeBean bean) {
        CommentQuestionListFragment fragment = new CommentQuestionListFragment();

        Bundle sendBundle = new Bundle();
        sendBundle.putString("tag", tag);
        sendBundle.putSerializable("bean", bean);
        fragment.setArguments(sendBundle);
        return fragment;
    }

    @Override
    public int setViewId() {
        return R.layout.fragment_comment_question_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        mTag = bundle.getString("tag");
        CommentQuestionModeBean bean = (CommentQuestionModeBean) bundle.getSerializable("bean");
        List<DaoThemeBean> list = bean.getWhereCondition(mTag);
        showView(list);

        btn = mView.findViewById(R.id.fragment_comment_question_list_btn);
        if (TextUtils.equals(bean.everyday, bean.title)) btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(v -> upData());
    }

    private void upData() {
        List<DaoThemeBean> list = mAdapter.mList;
        for (int i = 0; i < list.size(); i++) {
            DaoThemeBean bean = DataUtil.setAnswer(list.get(i));
            list.set(i, bean);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void showView(List<DaoThemeBean> list) {

        if (list == null || list.size() <= 0) {
            showEmptyView();
            return;
        }
        showDataView();
        RecyclerView rv = mView.findViewById(R.id.fragment_comment_question_list_rv);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mAdapter = new CommentQuestionAdapter(getContext(), list);
        rv.setAdapter(mAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(EventCommentQuestionBean bean) {
        mAdapter.upItemView(bean.appID);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
