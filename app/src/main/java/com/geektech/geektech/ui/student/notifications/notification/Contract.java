package com.geektech.geektech.ui.student.notifications.notification;

import android.os.Bundle;

public interface Contract {
    interface View {
        void openEditFragment(Bundle arguments);
    }

    interface Presenter {
        void onLinearLayoutStudentNameWasClicked();
        void onLinearLayoutStudentGroupWasClicked();
    }

    interface Model {
        String loadMessage();
    }
}