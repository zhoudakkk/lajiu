package com.laojiu.app.bean;


public class StemBean {
    public String stem;
    public String answer;
    public String historyAnswer;

    public String myAnswer;

    @Override
    public String toString() {
        return "StemBean{" +
                "stem='" + stem + '\'' +
                ", answer='" + answer + '\'' +
                ", historyAnswer='" + historyAnswer + '\'' +
                ", myAnswer='" + myAnswer + '\'' +
                '}';
    }
}
