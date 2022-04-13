package com.laojiu.app.utils;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.db.gen.DaoThemeBeanDao;

import java.util.List;

public class DataUtil {

    public static List<DaoThemeBean> getQuestionData(int pager) {
        pager = pager + 1;
        return APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.Type.eq(AppContent.QuestionType), DaoThemeBeanDao.Properties.TagID.between(pager, 10 + pager)).list();
    }
}
