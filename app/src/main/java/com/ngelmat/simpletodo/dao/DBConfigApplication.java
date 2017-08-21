package com.ngelmat.simpletodo.dao;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class DBConfigApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // instantiate for DBFlow
        FlowManager.init(new FlowConfig.Builder(this).build());

    }
}
