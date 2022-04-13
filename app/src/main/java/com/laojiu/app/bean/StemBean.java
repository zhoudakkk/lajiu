package com.laojiu.app.bean;

public class StemBean {
    public String stem;
    public String answer;
    public String myAnswer;
    public boolean isShowAnswer = false;

    @Override
    public String toString() {
        return "StemBean{" +
                "stem='" + stem + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
