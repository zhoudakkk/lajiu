package com.laojiu.app.utils;

import android.text.TextUtils;

import com.laojiu.app.APP;
import com.laojiu.app.AppContent;
import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.StemBean;
import com.laojiu.app.db.gen.DaoSession;
import com.laojiu.app.db.gen.DaoThemeBeanDao;

import org.greenrobot.greendao.query.WhereCondition;

import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    public static List<DaoThemeBean> getQuestionData(int pager) {
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

    public static List<DaoThemeBean> getAllData(String type, WhereCondition condition) {

        return APP.getDaoSession().getDaoThemeBeanDao().queryBuilder().
                where(DaoThemeBeanDao.Properties.Type.eq(type), condition).list();
    }

    public static List<DaoThemeBean> getAllCompleteData(String type) {
        WhereCondition condition = DaoThemeBeanDao.Properties.CompleteNumber.gt(0);
        return getAllData(type, condition);
    }

    public static List<DaoThemeBean> getAllIncompleteData(String type) {
        WhereCondition condition = DaoThemeBeanDao.Properties.CompleteNumber.le(0);
        return getAllData(type, condition);

    }

    public static List<DaoThemeBean> getAllSignData(String type) {
        WhereCondition condition = DaoThemeBeanDao.Properties.IsSign.eq(true);
        return getAllData(type, condition);
    }

    public static List<DaoThemeBean> getAllErrorData(String type) {
        WhereCondition condition = DaoThemeBeanDao.Properties.IsError.eq(true);
        return getAllData(type, condition);
    }

    public static List<DaoThemeBean> getNumberData(String type) {
        int number = SpUtils.getInt(type);
        WhereCondition condition = DaoThemeBeanDao.Properties.TagID.between(number, number + 10);
        return getAllData(type, condition);
    }

    /**
     * @param type
     * @return
     */
    public static List<DaoThemeBean> getAnswerData(String type, String str) {

        List<DaoThemeBean> data = getAllData(type);
        List<DaoThemeBean> dataNew = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            DaoThemeBean item = data.get(i);
            List<StemBean> list = item.stemBeanList;
            for (int n = 0; n < list.size(); n++) {
                String answer = list.get(n).answer;
                if (!TextUtils.isEmpty(answer) && answer.contains(str)) {
                    dataNew.add(item);
                    continue;
                }
            }
        }
        return dataNew;
    }

    public static List<DaoThemeBean> getThemeData(String type, String str) {
        WhereCondition condition = DaoThemeBeanDao.Properties.Theme.like("%" + str + "%");
        return getAllData(type, condition);
    }


    public static List<DaoThemeBean> getRandomData(String type) {
        WhereCondition condition = DaoThemeBeanDao.Properties.IsError.eq(true);
        return getAllData(type, condition);
    }


    /**
     * ??????id??????
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
     * ??????????????????
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
     * ????????????
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
     * ??????????????????
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
     * ???????????????
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
