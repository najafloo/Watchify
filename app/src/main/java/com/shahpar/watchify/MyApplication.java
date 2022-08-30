package com.shahpar.watchify;

import android.app.Application;

import com.shahpar.watchify.translateor.TranslateAPI;

public class MyApplication extends Application {

    private static TranslateAPI translator;
    private static NotificationRepresenter notifyRepresenter;
    private static MyApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        translator = new TranslateAPI();
        notifyRepresenter = new NotificationRepresenter();
    }

    public static MyApplication getContext() {
        return appContext;
    }

    public static TranslateAPI getTranslator() {
        return translator;
    }

    public static NotificationRepresenter getNotificationRepresnter() {
        return notifyRepresenter;
    }
}
