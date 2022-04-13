package com.laojiu.app.bean;

public class WelcomeBean {
    public String type;
    public int tag;
    public int length;
    public String str;

    public WelcomeBean(String type, int tag, int length) {
        this.type = type;
        this.tag = tag;
        this.length = length;
    }

    @Override
    public String toString() {
        return "WelcomeBean{" +
                "type='" + type + '\'' +
                ", tag=" + tag +
                ", length=" + length +
                ", str='" + str + '\'' +
                '}';
    }
}
