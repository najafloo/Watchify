package com.shahpar.watchify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.shahpar.watchify.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    ActivityAboutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(view -> finish());

        final String TAG = "SHAHPAR";

        binding.btnWhatsapp.setOnClickListener(view -> {
            Log.d(TAG, "onCreate: ");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.makeWhatsAppLink("+989359450963")));
            startActivity(myIntent);
        });

        binding.btnTelegram.setOnClickListener(view -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Utils.makeTelegramLink("+989359450963")));
            startActivity(myIntent);
        });

        binding.btnLinkedin.setOnClickListener(view -> {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/amin-najafloo-38753b79"));
            startActivity(myIntent);
        });
    }
}

