package com.example.jeansmits.jsonexercise.utils;

import android.app.Application;

import com.example.jeansmits.jsonexercise.BuildConfig;

import timber.log.Timber;

public class VDABApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}