package com.kirer;


import cn.bmob.v3.Bmob;

/**
 * Created by tiptimes on 15/8/6.
 */
public class App extends android.app.Application {

    private static final String BMOB_KEY = "5da2ec5f294e0ef6a36d638a1fe0058b";

    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Bmob.initialize(this,BMOB_KEY);
    }

    public static App getInstance(){
        return mApp;
    }
}
