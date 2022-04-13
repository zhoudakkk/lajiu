package com.laojiu.app.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiu.app.R;
import com.laojiu.app.adapter.CenterAdapter;
import com.laojiu.app.base.BaseFragment;


public class CenterFragment extends BaseFragment {

    @Override
    public int setViewId() {
        return R.layout.fragment_center;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        initRv();
    }

    private void initRv() {
        RecyclerView mRv = mView.findViewById(R.id.center_rv);
        mRv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mRv.setAdapter(new CenterAdapter(getContext()));

    }
}
