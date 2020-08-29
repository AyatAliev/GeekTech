package com.geektech.geektech.ui.model;

public class TestAppModels {
    private int imageApp;
    private String textNameApp;
    private String textDescApp;

    public TestAppModels(int imageApp, String textNameApp, String textDescApp) {
        this.imageApp = imageApp;
        this.textNameApp = textNameApp;
        this.textDescApp = textDescApp;
    }

    public int getImageApp() {
        return imageApp;
    }

    public void setImageApp(int imageApp) {
        this.imageApp = imageApp;
    }

    public String getTextNameApp() {
        return textNameApp;
    }

    public void setTextNameApp(String textNameApp) {
        this.textNameApp = textNameApp;
    }

    public String getTextDescApp() {
        return textDescApp;
    }

    public void setTextDescApp(String textDescApp) {
        this.textDescApp = textDescApp;
    }
}
