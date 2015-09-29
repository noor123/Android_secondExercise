package com.example.jeansmits.jsonexercise.utils;

import android.app.Application;

import com.example.jeansmits.jsonexercise.BuildConfig;
import com.parse.Parse;
import com.parse.ParseInstallation;

import timber.log.Timber;

public class VDABApplication extends Application {

    @Override
    public void onCreate() {

        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "SRv18T1RPs3HT1bKQGtuy0JCdWTOsmBk1dplyQr6", "s9lKqt3o8Xe9iBGNtaVA5klQI79mac6uBjvAk4bB");
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

}