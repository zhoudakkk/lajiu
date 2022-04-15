package com.laojiu.app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.R;
import com.laojiu.app.base.BaseActivity;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.WelcomeBean;
import com.laojiu.app.db.gen.DaoThemeBeanDao;
import com.laojiu.app.utils.SpUtils;
import com.laojiu.app.utils.TxtUtils;
import com.laojiu.app.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    private AppCompatTextView questionTv;
    private AppCompatTextView reasonTv;
    private AppCompatTextView methodTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        EventBus.getDefault().register(this);
        questionTv = findViewById(R.id.activity_question_tv);
        reasonTv = findViewById(R.id.activity_reason_tv);
        methodTv = findViewById(R.id.activity_method_tv);

        findViewById(R.id.activity_welcome_btn).setOnClickListener(v -> gotoNext());
        findViewById(R.id.activity_welcome_iv).setOnClickListener(v -> getDataAgain());
        boolean b = SpUtils.getBoolean(AppContent.isFirst);
        if (!b) {
            initData();
        } else {
            MainActivity.gotoActivity(this);
            finish();
        }

    }

    @SuppressLint("CheckResult")
    private void initData() {


        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        final long count = dao.queryBuilder().count();

        Observable.create((ObservableOnSubscribe<List<DaoThemeBean>>) e -> {
            if (count != 0) dao.detachAll();
            String mContent = TxtUtils.getQuestionTxt(WelcomeActivity.this);
            List<DaoThemeBean> themeList = TxtUtils.getThemeList(mContent, AppContent.QuestionType);
            e.onNext(themeList);
        }).doOnNext(list -> {
            DaoThemeBeanDao daoQuestion = APP.getDaoSession().getDaoThemeBeanDao();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                daoQuestion.insert(list.get(i));
                int n = i + 1;
                EventBus.getDefault().post(new WelcomeBean(AppContent.QuestionType, n, size));
            }

        }).map(list -> {
            String mContent = TxtUtils.getReasonTxt(WelcomeActivity.this);
            List<DaoThemeBean> themeList = TxtUtils.getThemeList(mContent, AppContent.ReasonType);
            Log.e("text123", "理由 长度  themeList = " + themeList.size());
            return themeList;
        }).doOnNext(list -> {
            DaoThemeBeanDao daoReason = APP.getDaoSession().getDaoThemeBeanDao();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                daoReason.insert(list.get(i));
                int n = i + 1;
                EventBus.getDefault().post(new WelcomeBean(AppContent.ReasonType, n, size));
            }
        }).map(daoThemeBeans -> {
            String mContent = TxtUtils.getMethodTxt(WelcomeActivity.this);
            List<DaoThemeBean> themeList = TxtUtils.getThemeList(mContent, AppContent.MethodType);
            return themeList;
        }).doOnNext(list -> {
            DaoThemeBeanDao daoReason = APP.getDaoSession().getDaoThemeBeanDao();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                daoReason.insert(list.get(i));
                int n = i + 1;
                EventBus.getDefault().post(new WelcomeBean(AppContent.MethodType, n, size));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(list -> {
            SpUtils.putBoolean(AppContent.isFirst, true);
            SpUtils.putInt(AppContent.QuestionType, 0);
            SpUtils.putInt(AppContent.ReasonType, 0);
            SpUtils.putInt(AppContent.MethodType, 0);
        });
    }

    private void getDataAgain() {
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        long count = dao.count();
        if (count > 0) dao.detachAll();
        initData();
    }

    private void gotoNext() {
        boolean b = SpUtils.getBoolean(AppContent.isFirst);
        if (!b) {
            Utils.showToast("初始化失败,请点击头像从新加载");
            return;
        }
        MainActivity.gotoActivity(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(WelcomeBean bean) {
        if (TextUtils.equals(AppContent.QuestionType, bean.type)) {
            questionTv.setText("导入问题资源文件( + " + bean.tag + " + / + " + bean.length + ")");
        }
        if (TextUtils.equals(AppContent.ReasonType, bean.type)) {
            reasonTv.setText("导入原因资源文件( + " + bean.tag + " + / + " + bean.length + ")");
        }

        if (TextUtils.equals(AppContent.MethodType, bean.type)) {
            methodTv.setText("导入对策资源文件( + " + bean.tag + " + / + " + bean.length + ")");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
