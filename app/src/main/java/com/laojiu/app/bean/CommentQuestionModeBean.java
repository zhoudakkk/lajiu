package com.laojiu.app.bean;

import com.laojiu.app.utils.DataUtil;

import java.io.Serializable;
import java.util.List;

public class CommentQuestionModeBean implements Serializable {

    public final static String all = "全部";
    public final static String completed = "已完成";
    public final static String incomplete = "未完成";
    public final static String sign = "标星";
    public final static String error = "错题";

    public final static String everyday = "来30";

    public final static String answer = "答案逆推";
    public int type;
    public int image;
    public String title;

    public CommentQuestionModeBean(int type) {
        this.type = type;
    }

    public CommentQuestionModeBean(int type, int image, String title) {
        this.type = type;
        this.image = image;
        this.title = title;
    }

    public List<DaoThemeBean> getWhereCondition(String tag) {
        List<DaoThemeBean> data = null;
        switch (title) {
            case all:
                data = DataUtil.getAllData(tag);
                break;
            case completed:
                data = DataUtil.getAllCompleteData(tag);
                break;
            case incomplete:
                data = DataUtil.getAllIncompleteData(tag);
                break;
            case sign:
                data = DataUtil.getAllSignData(tag);
                break;
            case error:
                data = DataUtil.getAllErrorData(tag);
                break;
            case everyday:
                data = DataUtil.getNumberData(tag);
                break;
        }
        return data;


    }
}
