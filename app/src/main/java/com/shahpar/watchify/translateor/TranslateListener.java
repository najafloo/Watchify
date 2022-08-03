package com.shahpar.watchify.translateor;

public interface TranslateListener {

    void onSuccess(String translatedText);

    void onFailure(String ErrorText);

}
