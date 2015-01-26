package com.diegosantos.parsedataoffline;

import android.app.Application;
import android.content.res.Configuration;

import com.parse.Parse;

public class MyApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        // Get your keys from parse.com
        Parse.initialize(this, Constants.KEY1, Constants.KEY2);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}