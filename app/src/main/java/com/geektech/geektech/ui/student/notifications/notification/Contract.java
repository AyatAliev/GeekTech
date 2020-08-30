package com.geektech.geektech.ui.student.notifications.notification;

import android.os.Bundle;

public interface Contract {
    interface View {
        void openEditFragment(Bundle arguments);
        void openTestFragment();
    }

    interface Presenter {
        void onLinearLayoutStudentNameWasClicked();
        void onLinearLayoutStudentGroupWasClicked();
        void onTextViewWebViewWasClicked();
    }

    interface Model {
        String loadMessage();
    }
}