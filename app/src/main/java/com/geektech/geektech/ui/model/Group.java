package com.geektech.geektech.ui.model;

import android.net.Uri;

import java.io.Serializable;

public class Group implements Serializable {
    private Uri imageView;

    public Group() { }

    public Group(String title) {
        this.title = title;
    }

    public Uri getImageView() {
        return imageView;
    }

    public void setImageView(Uri imageView) {
        this.imageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}