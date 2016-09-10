package com.sequoiahack.storylead.app;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.sequoiahack.storylead.controller.data.tablemodels.CallData;

/**
 * Application class for app
 * Created by zac on 10/09/16.
 */
public class StoryLeadApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDB();
    }

    protected void initializeDB() {
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClasses(CallData.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }
}
