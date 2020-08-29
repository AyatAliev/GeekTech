package com.geektech.geektech.presenter;

import android.widget.Toast;

public class Toaster {

    public static void show(String message) {
        Toast.makeText(App.getInstance().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
