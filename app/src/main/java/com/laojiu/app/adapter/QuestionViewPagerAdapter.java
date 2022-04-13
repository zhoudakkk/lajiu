package com.laojiu.app.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.laojiu.app.ui.fragment.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

public class QuestionViewPagerAdapter extends FragmentPagerAdapter {

    List<QuestionFragment> mList = new ArrayList<>();

    public QuestionViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
        mList.add(new QuestionFragment());
    }


    @NonNull
    @Override
    public QuestionFragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
