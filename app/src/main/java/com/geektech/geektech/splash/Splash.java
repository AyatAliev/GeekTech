package com.geektech.geektech.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.geektech.MainActivity;
import com.geektech.geektech.R;


public class Splash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    private NavController navController;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            Splash.this.startActivity(mainIntent);
            Splash.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}
