package com.laojiu.app.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;


@Entity
public class DaoThemeBean {

    @Id(autoincrement = true)
    private Long id;


    public Long appID;

    public Long tagID;


    public String theme;

    /**
     * 完成次数
     */
    public Long completeNumber = Long.valueOf(0);

    //"1"
    public Boolean isSign = false;

    public Boolean isError = false;


    /**
     * 0 是问题
     * 1 是原因
     * 2 是对策
     */
    public String type;

    @Convert(columnType = String.class, converter = StemBean_Converter.class)
    public List<StemBean> stemBeanList;

    @Generated(hash = 483888184)
    public DaoThemeBean(Long id, Long appID, Long tagID, String theme,
                        Long completeNumber, Boolean isSign, Boolean isError, String type,
                        List<StemBean> stemBeanList) {
        this.id = id;
        this.appID = appID;
        this.tagID = tagID;
        this.theme = theme;
        this.completeNumber = completeNumber;
        this.isSign = isSign;
        this.isError = isError;
        this.type = type;
        this.stemBeanList = stemBeanList;
    }

    @Generated(hash = 1264352840)
    public DaoThemeBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTagID() {
        return tagID;
    }

    public void setTagID(Long tagID) {
        this.tagID = tagID;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getCompleteNumber() {
        return completeNumber;
    }

    public void setCompleteNumber(Long completeNumber) {
        this.completeNumber = completeNumber;
    }

    public Boolean getSign() {
        return isSign;
    }

    public void setSign(Boolean sign) {
        isSign = sign;
    }

    public Boolean getError() {
        return isError;
    }

    public void setError(Boolean error) {
        isError = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StemBean> getStemBeanList() {
        return stemBeanList;
    }

    public void setStemBeanList(List<StemBean> stemBeanList) {
        this.stemBeanList = stemBeanList;
    }

    public Boolean getIsSign() {
        return this.isSign;
    }

    public void setIsSign(Boolean isSign) {
        this.isSign = isSign;
    }

    public Boolean getIsError() {
        return this.isError;
    }

    public void setIsError(Boolean isError) {
        this.isError = isError;
    }

    public Long getAppID() {
        return appID;
    }

    public void setAppID(Long appID) {
        this.appID = appID;
    }
}
