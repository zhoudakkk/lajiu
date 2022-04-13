package com.laojiu.app.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import com.laojiu.app.R;
import com.laojiu.app.utils.StatusBarUtil;


public class TitleView extends Toolbar {

    private TitleViewAttrs mAttrs = new TitleViewAttrs();
    private Context mContext;
    private static int mBarHeight = 0;
    public ConstraintLayout mTitleCl;
    public AppCompatButton mBackBtn;
    public AppCompatTextView mTitleTv;
    public AppCompatButton mRightBtn;
    private Guideline mTitleGl;

    public TitleView(Context context) {
        super(context);
        initView(context, null);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mContext = context;
        initAttributeSet(attrs);
        findView();
        setTitleBarView();
        initTitle();
    }


    private void initAttributeSet(AttributeSet attrs) {

        TypedArray mTypedArray = mContext.obtainStyledAttributes(attrs, R.styleable.TitleView);

        mAttrs.mShowLeft = mTypedArray.getBoolean(R.styleable.TitleView_showLeft, true);
        mAttrs.mTextLeft = mTypedArray.getString(R.styleable.TitleView_textLeft);
        mAttrs.mDrawableIconLeft = mTypedArray.getDrawable(R.styleable.TitleView_iconLeft);

        mAttrs.mTitle = mTypedArray.getString(R.styleable.TitleView_textTitle);
        mAttrs.mDrawableIconTitle = mTypedArray.getDrawable(R.styleable.TitleView_iconTitle);

        mAttrs.mTextRight = mTypedArray.getString(R.styleable.TitleView_textRight);
        mAttrs.mDrawableIconRight = mTypedArray.getDrawable(R.styleable.TitleView_iconRight);

        mAttrs.mShowOnlyBar = mTypedArray.getBoolean(R.styleable.TitleView_showOnlyBar, false);
        mTypedArray.recycle();
    }

    private void findView() {

        setContentInsetsRelative(0, 0);
        View.inflate(mContext, R.layout.app_base_title, this);
        setElevation(4);
        mTitleCl = findViewById(R.id.title_cl);
        mTitleGl = findViewById(R.id.title_gl);

        mBackBtn = findViewById(R.id.title_back_btn);
        mTitleTv = findViewById(R.id.title_tv);
        mRightBtn = findViewById(R.id.title_right_btn);


        mBackBtn.setOnClickListener(mClickListener);
    }

    private void setTitleBarView() {
        //高度设置
        if (mBarHeight == 0) mBarHeight = StatusBarUtil.getStatusBarHeight(mContext);
        mTitleGl.setGuidelineBegin(mBarHeight);

        //状态栏的颜色设置
        if (isInEditMode()) return;
        StatusBarUtil.setStatusBarColor(mContext);
    }

//    public void setStatusBarColor(Context context,int color){
//        StatusBarUtil.setStatusBarColor((BaseActivity) mContext);
//    }

    private void initTitle() {

        if (!mAttrs.mShowLeft) mBackBtn.setVisibility(GONE);
        if (!TextUtils.isEmpty(mAttrs.mTextLeft)) mBackBtn.setText(mAttrs.mTextLeft);
        if (mAttrs.mDrawableIconLeft != null)
            mBackBtn.setCompoundDrawables(mAttrs.mDrawableIconLeft, null, null, null);

        if (!TextUtils.isEmpty(mAttrs.mTitle)) mTitleTv.setText(mAttrs.mTitle);
        if (mAttrs.mDrawableIconTitle != null)
            mTitleTv.setCompoundDrawables(null, null, mAttrs.mDrawableIconTitle, null);

        if (!TextUtils.isEmpty(mAttrs.mTextRight)) {
            mRightBtn.setVisibility(VISIBLE);
            mRightBtn.setText(mAttrs.mTextRight);
        }
        if (mAttrs.mDrawableIconRight != null) {
            mRightBtn.setVisibility(VISIBLE);
            mRightBtn.setCompoundDrawables(null, null, mAttrs.mDrawableIconRight, null);
        }

        if (mAttrs.mShowOnlyBar) {
            mBackBtn.setVisibility(GONE);
            mTitleTv.setVisibility(GONE);
        }
    }

    /**
     * 界面预览需要
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * title属性类
     */
    private class TitleViewAttrs {

        public boolean mShowLeft = true;
        public String mTextLeft = "";
        public Drawable mDrawableIconLeft = null;

        public String mTitle = "";
        public Drawable mDrawableIconTitle = null;

        public String mTextRight = "";
        public Drawable mDrawableIconRight = null;


        public boolean mShowOnlyBar = false;

    }

    OnClickListener mClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isInEditMode()) return;
            if (mContext instanceof Activity) ((Activity) mContext).finish();
        }
    };
}
