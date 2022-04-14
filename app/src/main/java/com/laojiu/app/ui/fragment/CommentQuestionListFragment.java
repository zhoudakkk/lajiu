package com.laojiu.app.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.adapter.CommentQuestionAdapter;
import com.laojiu.app.base.BaseFragment;
import com.laojiu.app.bean.EventCommentQuestionBean;
import com.laojiu.app.utils.DataUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class CommentQuestionListFragment extends BaseFragment {

    private String mTag = AppContent.QuestionType;
    private CommentQuestionAdapter mAdapter;

    public static CommentQuestionListFragment getInstance(String tag) {
        CommentQuestionListFragment fragment = new CommentQuestionListFragment();

        Bundle sendBundle = new Bundle();
        sendBundle.putString("tag", tag);
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

        RecyclerView rv = mView.findViewById(R.id.fragment_comment_question_list_rv);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mAdapter = new CommentQuestionAdapter(getContext(), DataUtil.getAllData(mTag));
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
