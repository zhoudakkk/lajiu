package com.laojiu.app.utils;


import android.content.Context;
import android.text.TextUtils;

import com.laojiu.app.bean.DaoThemeBean;
import com.laojiu.app.bean.StemBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtUtils {

    public static String getQuestionTxt(Context context) throws IOException {
        InputStream is = context.getAssets().open("001");
        return readTxt(is);
    }

    public static String getReasonTxt(Context context) throws IOException {
        InputStream is = context.getAssets().open("002");
        return readTxt(is);
    }

    public static String getMethodTxt(Context context) throws IOException {
        InputStream is = context.getAssets().open("003");
        return readTxt(is);
    }

    public static String readTxt(InputStream stream) {
        String str = "";
        try {

            InputStreamReader isr = new InputStreamReader(stream);
            BufferedReader br = new BufferedReader(isr);

            String mimeTypeLine = null;
            while ((mimeTypeLine = br.readLine()) != null) {
                str = str + mimeTypeLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


    public static List<DaoThemeBean> getThemeList(String str, String type) {
        List<DaoThemeBean> list = new ArrayList<>();
        if (TextUtils.isEmpty(str)) return list;
        Date date = new Date();
        String[] allList = str.split("题目");
        for (int i = 0; i < allList.length; i++) {
            String allStr = allList[i];
            DaoThemeBean bean = getThemeBean(allStr);

            if (bean != null) {
                bean.tagID = Long.parseLong("" + i);
                bean.appID = date.getTime() + i;
                bean.type = type;
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * :农村劳动动力流失
     * <p>
     * 材料1:
     * 《2001年世界发展指标》的数据显印度的8。
     * 本段采分点: 劳动生产率低
     * <p>
     * 材料2:
     * 在各种转移方式中,
     * 本段采分点: 亲缘性
     *
     * @param str
     * @return
     */

    private static DaoThemeBean getThemeBean(String str) {
        if (TextUtils.isEmpty(str)) return null;
        DaoThemeBean themeBean = new DaoThemeBean();
        String[] msList = str.split("材料");

        String title = msList[0];

        themeBean.theme = title.replaceAll(":", "").trim();

        List<StemBean> stemBeanList = new ArrayList<>();
        for (int i = 1; i < msList.length; i++) {
            StemBean bean = new StemBean();
            String stemStr = msList[i];
            String[] stemList = stemStr.split("本段采分点");

            String stem = stemList[0];
            bean.stem = stem;
            if (stem.length() > 2) bean.stem = stem.substring(2, stem.length()).trim();


            if (stemList.length > 1) {
                String answer = stemList[1];
                bean.answer = answer.replaceAll(":", "").trim();
            }
            stemBeanList.add(bean);
        }
        themeBean.stemBeanList = stemBeanList;
        return themeBean;
    }

}
