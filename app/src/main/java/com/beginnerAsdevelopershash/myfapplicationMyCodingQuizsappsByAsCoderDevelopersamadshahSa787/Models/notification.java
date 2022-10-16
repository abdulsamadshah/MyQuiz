package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models;

public class notification {
    private String title;
    private String description;
    private String nimage;

    public notification(String title, String description, String nimage) {
        this.title = title;
        this.description = description;
        this.nimage = nimage;
    }

    public notification() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNimage() {
        return nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage;
    }
}
