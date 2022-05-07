package com.laojiu.app;

import android.app.Application;
import android.content.Context;

import com.laojiu.app.db.gen.DaoMaster;
import com.laojiu.app.db.gen.DaoSession;
import com.laojiu.app.db.gen.DaoThemeBeanDao;

public class APP extends Application {
    public static Context mContext;
    public static DaoMaster daoMaster;
    public static DaoSession daoSession;
    public DaoMaster.DevOpenHelper devOpenHelper = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "LJ.db", null);
        //实例化DaoMaster对象
        daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        //实例化DaoSession对象
        daoSession = daoMaster.newSession();
    }

    //通过此方法,进行增删改查
    public static DaoSession getDaoSession() {


        return daoSession;
    }

    public static void resetAll(){
        DaoMaster.dropAllTables(daoMaster.getDatabase(),true);
        DaoMaster.createAllTables(daoMaster.getDatabase(),true);
        DaoThemeBeanDao dao = APP.getDaoSession().getDaoThemeBeanDao();
        dao.deleteAll();
    }

}
