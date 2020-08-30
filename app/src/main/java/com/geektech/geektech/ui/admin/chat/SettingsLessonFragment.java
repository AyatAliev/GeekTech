package com.geektech.geektech.ui.admin.chat;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.geektech.geektech.R;

public class SettingsLessonFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}