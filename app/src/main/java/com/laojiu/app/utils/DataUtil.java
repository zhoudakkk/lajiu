package com.laojiu.app.utils;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.StemBean;
import com.laojiu.app.db.gen.DaoThemeBeanDao;

import java.nio.ShortBuffer;
import java.util.List;

public class DataUtil {

    public static List<DaoThemeBean> getQuestionData(int pager) {
        pager = pager + 1;
        return APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.Type.eq(AppContent.QuestionType), DaoThemeBeanDao.Properties.TagID.between(pager, 10 + pager)).list();
    }

    public static List<DaoThemeBean> getAllQuestionData() {

        return APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.Type.eq(AppContent.QuestionType)).list();
    }


    public static List<DaoThemeBean> getAllData(String type) {

        return APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.Type.eq(type)).list();
    }


    /**
     * 通过id查找
     *
     * @param appID
     * @return
     */
    public static DaoThemeBean getDataByAppID(long appID) {
        List<DaoThemeBean> list = APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.AppID.eq(appID)).list();

        if (list == null || list.size() <= 0) return null;

        return list.get(0);
    }

    /**
     * 清空答题答案
     *
     * @param appID
     * @return
     */
    public static DaoThemeBean getClearAnswerByAppID(long appID) {

        List<DaoThemeBean> list = APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.AppID.eq(appID)).list();

        if (list == null || list.size() <= 0) return null;
        DaoThemeBean bean = list.get(0);
        if (bean == null) return null;
        for (int i = 0; i < bean.stemBeanList.size(); i++) {
            bean.stemBeanList.get(i).myAnswer = "";
        }
        APP.getDaoSession().getDaoThemeBeanDao().update(bean);
        return bean;
    }

    /**
     * 设置标记
     *
     * @param item
     */
    public static void setSign(DaoThemeBean item) {
        if (item.isSign == null) item.isSign = false;
        item.isSign = !item.isSign;
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        dao.update(item);
    }

    /**
     * 设置错误标记
     *
     * @param item
     */
    public static void setError(DaoThemeBean item) {
        if (item.isError == null) item.isError = false;
        item.isError = !item.isError;
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        dao.update(item);
    }

    /**
     * 清空历记录
     *
     * @param item
     */
    public static DaoThemeBean clearHistoryAnswer(DaoThemeBean item) {
        if (item == null) return null;
        for (int i = 0; i < item.stemBeanList.size(); i++) {
            item.stemBeanList.get(i).historyAnswer = "";
        }
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        dao.update(item);
        return item;
    }


    public static DaoThemeBean setAnswer(DaoThemeBean item) {
        if (item == null) return null;
        if (item.completeNumber == null) item.completeNumber = Long.valueOf(0);
        item.completeNumber++;
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        dao.update(item);
        return item;
    }

}