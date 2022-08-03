package com.shahpar.watchify;

import android.app.Application;

import com.shahpar.watchify.translateor.TranslateAPI;

public class MyApplication extends Application {

    private static TranslateAPI translator;

    @Override
    public void onCreate() {
        super.onCreate();

        translator = new TranslateAPI();
    }

    public static TranslateAPI getTranslator() {
        return translator;
    }
}
