package com.laojiu.app.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.List;

public class StemBean_Converter implements PropertyConverter<List<StemBean>, String> {
    @Override
    public List<StemBean> convertToEntityProperty(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }
        TypeToken<List<StemBean>> typeToken = new TypeToken<List<StemBean>>() {
        };
        return new Gson().fromJson(databaseValue, typeToken.getType());
    }

    @Override
    public String convertToDatabaseValue(List<StemBean> arrays) {
        if (arrays == null || arrays.size() == 0) {
            return null;
        } else {
            String sb = new Gson().toJson(arrays);
            return sb;

        }
    }
}
