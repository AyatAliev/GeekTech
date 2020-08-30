package com.geektech.geektech.ui.student.notifications.notification;

import android.os.Bundle;


import static com.geektech.geektech.variable_constants.Const.KEY_EDIT_FRAGMENT_ARGUMENTS;

public class Presenter implements Contract.Presenter {
    private Contract.View mView;
    private Contract.Model mModel;

    public Presenter(Contract.View mView) {
        this.mView = mView;
        this.mModel = new Model();
    }

    @Override
    public void onLinearLayoutStudentNameWasClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_EDIT_FRAGMENT_ARGUMENTS, "0\n" + mModel.loadMessage());
        mView.openEditFragment(bundle);
    }

    @Override
    public void onLinearLayoutStudentGroupWasClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_EDIT_FRAGMENT_ARGUMENTS, "1\n" + mModel.loadMessage());
        mView.openEditFragment(bundle);
    }

    @Override
    public void onTextViewWebViewWasClicked() {
        mView.openTestFragment();
    }
}