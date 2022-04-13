package com.laojiu.app.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laojiu.app.utils.StatusBarUtil;

public class BaseActivity extends AppCompatActivity {

    private InputMethodManager methodManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        StatusBarUtil.setStatusBarColor(this);
        methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

    }

    public void hideSoftInput(View view) {
        if (methodManager == null) return;
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void hideSoftInput() {
        View view = getCurrentFocus();
        if (view instanceof EditText) view.clearFocus();
        if (methodManager == null) return;
        if (view == null) return;
        methodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
