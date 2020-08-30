package com.geektech.geektech.ui.model;


import java.io.Serializable;
import java.util.List;

public class Group implements Serializable {
    private String id;
    private String title;
    private String imageUri;
    private String sensei;
    private List<Student> students;

    public Group() { }

    public Group(String title, String imageUri, String sensei, List<Student> students) {
        this.title = title;
        this.imageUri = imageUri;
        this.sensei = sensei;
        this.students = students;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageView(String imageUrl) {
        this.imageUri = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getSensei() {
        return sensei;
    }

    public void setSensei(String sensei) {
        this.sensei = sensei;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}