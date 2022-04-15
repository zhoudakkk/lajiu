package com.laojiu.app.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.laojiu.app.AppContent;
import com.laojiu.app.base.BaseFragment;
import com.laojiu.app.bean.CommentQuestionModeBean;
import com.laojiu.app.ui.fragment.CommentQuestionListFragment;

import java.util.ArrayList;
import java.util.List;

public class CommentQuestionVpAdapter extends FragmentPagerAdapter {

    private List<CommentQuestionListFragment> mList = new ArrayList<>();
    private List<String> mListStr = new ArrayList<>();

    public CommentQuestionVpAdapter(@NonNull FragmentManager fm, CommentQuestionModeBean bean) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        mList.add(CommentQuestionListFragment.getInstance(AppContent.QuestionType,bean));
        mListStr.add("内容概括");
        mList.add(CommentQuestionListFragment.getInstance(AppContent.ReasonType,bean));
        mListStr.add("原因概括");
        mList.add(CommentQuestionListFragment.getInstance(AppContent.MethodType,bean));
        mListStr.add("对策概括");
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListStr.get(position);
    }


    @NonNull
    @Override
    public BaseFragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
