package com.laojiu.app.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.base.BaseFragment;
import com.laojiu.app.ui.WelcomeActivity;
import com.laojiu.app.utils.SpUtils;

public class UserFragment extends BaseFragment {
    @Override
    public int setViewId() {
        return R.layout.fragment_user;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mView.findViewById(R.id.fragment_user_reset_btn).setOnClickListener(v -> resetAll());
    }

    private void resetAll() {
        SpUtils.putBoolean(AppContent.isFirst, false);
        WelcomeActivity.gotoActivity(getContext());
    }
}
