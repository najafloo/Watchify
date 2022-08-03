package com.shahpar.watchify;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;

import com.shahpar.watchify.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String TAG = "SHAHPAR";
    ActivityMainBinding binding;

    private final static String DESTINATION_LANGUAGE = "english";

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

        boolean isEnglish = isEnglish();

        binding.btnEnglish.setChecked(isEnglish);
        binding.btnGermany.setChecked(!isEnglish);

        startService(isEnglish);

        binding.grpGroupButton.setOnCheckedChangeListener((radioGroup, i) -> {
            boolean isEng = true;
            AppCompatRadioButton radioButton = findViewById(i);

            if (!radioButton.getTag().equals("eng")) {
                isEng = false;
            }

            saveDestination(isEng);
            startService(isEng);
        });
    }

    private void saveDestination(boolean isEnglish) {
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(DESTINATION_LANGUAGE, isEnglish);
        editor.apply();
    }

    private void startService(boolean isEnglish) {
        Intent serviceIntent = new Intent(this, NotificationListener.class);
        serviceIntent.putExtra(DESTINATION_LANGUAGE, isEnglish);
        startService(serviceIntent);
    }

    private boolean isEnglish() {
        sharedPreferences = getApplicationContext().getSharedPreferences("app_pref", MODE_PRIVATE);
        return sharedPreferences.getBoolean(DESTINATION_LANGUAGE, true);
    }

    private boolean checkHasAccess() {
        ComponentName cn = new ComponentName(getApplicationContext(), NotificationListener.class);
        String flat = Settings.Secure.getString(getApplicationContext().getContentResolver(), "enabled_notification_listeners");
        return (flat != null && flat.contains(cn.flattenToString()));
    }
}