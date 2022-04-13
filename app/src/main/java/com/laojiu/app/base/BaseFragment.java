package com.laojiu.app.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.laojiu.app.R;

public abstract class BaseFragment extends Fragment{

    private View mParentView;
    private FrameLayout mFragmentView;

    protected View mView;
    private FrameLayout.LayoutParams mParams;
    private View mEmptyView;

    public abstract int setViewId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mParentView == null) {
            mParentView = View.inflate(getContext(), R.layout.fragment_base, null);
            mFragmentView = mParentView.findViewById(R.id.fragment_base_fl);
            initStateView();
            initView(savedInstanceState);
        }
        return mParentView;
    }

    private void initStateView() {
        mView = View.inflate(getContext(), setViewId(), null);
        mFragmentView.addView(mView, 0);
    }

    public void initView(@Nullable Bundle savedInstanceState) {

    }


    public void showDataView() {
        mFragmentView.removeView(mEmptyView);
    }

    public void showEmptyView() {
        if (mEmptyView == null)
            mEmptyView = View.inflate(getContext(), R.layout.fragment_empty_view, null);
        View child = mFragmentView.getChildAt(1);
        if (child == null) mFragmentView.addView(mEmptyView, 1, getParams());
        mEmptyView.findViewById(R.id.fragment_empty_im).setOnClickListener(v -> getDataAgain());
    }


    /**
     * 如果界面有标题 布局将设置在标题下面
     *
     * @return
     */
    private FrameLayout.LayoutParams getParams() {
        if (mParams != null) return mParams;
        mParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (mView == null) return mParams;
        ConstraintLayout titleView = mView.findViewById(R.id.title_cl);
        if (titleView == null) return mParams;
        mParams.topMargin = titleView.getHeight();
        return mParams;
    }

    public void getDataAgain() {

    }


}
