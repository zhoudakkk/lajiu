package com.laojiu.app.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;

import org.greenrobot.greendao.annotation.Generated;


@Entity
public class DaoThemeBean {

    @Id(autoincrement = true)
    private Long id;


    public Long tagID;


    public String theme;

    /**
     * 0 是问题
     * 1 是原因
     * 2 是对策
     */
    public String type;

    @Convert(columnType = String.class, converter = StemBean_Converter.class)
    public List<StemBean> stemBeanList;


    @Generated(hash = 1193499453)
    public DaoThemeBean(Long id, Long tagID, String theme, String type,
            List<StemBean> stemBeanList) {
        this.id = id;
        this.tagID = tagID;
        this.theme = theme;
        this.type = type;
        this.stemBeanList = stemBeanList;
    }

    @Generated(hash = 1264352840)
    public DaoThemeBean() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<StemBean> getStemBeanList() {
        return stemBeanList;
    }

    public void setStemBeanList(List<StemBean> stemBeanList) {
        this.stemBeanList = stemBeanList;
    }
}
