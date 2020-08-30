package com.geektech.geektech.ui.model;

public class Student {
    private String id;
    private String name;
    private String group;
    private String avatarUrl;
    private boolean mentor;
    private boolean choice;
    private long followers;

    public Student() {
    }

    public Student(String id, String name, String group, String avatarUrl, boolean mentor) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.avatarUrl = avatarUrl;
        this.mentor = mentor;
    }

    public boolean isChoice() {
        return choice;
    }

    public void setChoice(boolean choice) {
        this.choice = choice;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getId() {
        return id;
    }

    public long getFollowers() {
        return followers;
    }

    public void following(){
        followers++;
    }

    public void aFollowing(){
        followers--;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public boolean isMentor() {
        return mentor;
    }

    public void setMentor(boolean mentor) {
        this.mentor = mentor;
    }
}
