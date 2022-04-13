package com.laojiu.app.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.laojiu.app.R;
import com.laojiu.app.base.BaseFragment;

public class UserFragment extends BaseFragment {
    @Override
    public int setViewId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mView.findViewById(R.id.user_btn).setOnClickListener(v -> showEmptyView());
    }
}
