package com.geektech.geektech.preferenceHelper;


import android.content.Context;
import android.content.SharedPreferences;

import com.geektech.geektech.variable_constants.Const;
import com.geektech.geektech.variable_constants.User;

import static com.geektech.geektech.variable_constants.Const.KEY_SHARED_PREFERENCES_STUDENT_OR_ADMIN;

public class PreferenceHelper {
    private static volatile PreferenceHelper instance;
    private SharedPreferences preferences;
    private String NAME = "setting";
    private String IS_SHOWN = "isShown";


    public PreferenceHelper(Context context) {
        instance = this;
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);

    }

    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) new PreferenceHelper(context);
        return instance;
    }

    public boolean isShown() {
        return preferences.getBoolean(IS_SHOWN, false);
    }

    public void setIsShow() {
        preferences.edit().putBoolean(IS_SHOWN, true).apply();
    }

    public void user(String value) {
        preferences
                .edit()
                .putString(KEY_SHARED_PREFERENCES_STUDENT_OR_ADMIN, value)
                .apply();
    }

    public String user(){
        return preferences.getString(KEY_SHARED_PREFERENCES_STUDENT_OR_ADMIN, User.NO_USER.name());
    }
}


