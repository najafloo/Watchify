package com.shahpar.watchify;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.shahpar.watchify.databinding.ActivityMainBinding;
import com.shahpar.watchify.translateor.Language;
import com.shahpar.watchify.translateor.TranslateListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    ActivityMainBinding binding;
    boolean isEnglish;

    private final static String DESTINATION_KEY = "english";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        if (!checkHasAccess()) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }

        isEnglish = isEnglish();

        setDestinationLanguage(isEnglish);

        binding.btnEnglish.setChecked(isEnglish);
        binding.btnGermany.setChecked(!isEnglish);

        binding.btnShow.setOnClickListener(view -> {
            if (isEnglish)
                MyApplication.getNotificationRepresnter().notifyMessage(getString(R.string.watchify_english), getString(R.string.watchify_english_message));
            else
                MyApplication.getNotificationRepresnter().notifyMessage(getString(R.string.watchify_germany), getString(R.string.watchify_gerany_message));
        });

        binding.grpGroupButton.setOnCheckedChangeListener((radioGroup, i) -> {
            AppCompatRadioButton radioButton = findViewById(i);

            isEnglish = radioButton.getTag().equals("eng");

            setDestinationLanguage(isEnglish);
        });

        binding.btnTranslate.setOnClickListener(view -> {
            MyApplication.getTranslator().translate(Objects.requireNonNull(binding.edtText.getText()).toString(), new TranslateListener() {
                @Override
                public void onSuccess(String translatedText) {
                    binding.edtTranslate.setText(translatedText);
                }

                @Override
                public void onFailure(String ErrorText) {
                    Toast.makeText(getApplicationContext(), "Translate failed", Toast.LENGTH_LONG).show();
                }
            });
        });

        startService();
    }

    private void setDestinationLanguage(boolean isEnglish) {
        saveDestination(isEnglish);

        String desLanguage;

        if (isEnglish)
            desLanguage = Language.ENGLISH;
        else
            desLanguage = Language.GERMAN;

        MyApplication.getTranslator().setDestinationLanguage(desLanguage);
    }

    private void saveDestination(boolean isEnglish) {
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DESTINATION_KEY, isEnglish);
        editor.commit();
    }

    private void startService() {
        Intent serviceIntent = new Intent(this, NotificationListener.class);
        startService(serviceIntent);
    }

    private boolean isEnglish() {
        sharedPreferences = getApplicationContext().getSharedPreferences("app_pref", MODE_PRIVATE);
        return sharedPreferences.getBoolean(DESTINATION_KEY, true);
    }

    private boolean checkHasAccess() {
        ComponentName cn = new ComponentName(getApplicationContext(), NotificationListener.class);
        String flat = Settings.Secure.getString(getApplicationContext().getContentResolver(), "enabled_notification_listeners");
        return (flat != null && flat.contains(cn.flattenToString()));
    }
}