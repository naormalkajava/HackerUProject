package com.example.ericbell.hackeruproject.firebase;

/**
 * Created by eric.bell on 6/29/2017.
 */

public class Ynet {

    private String image;
    private String link;
    private String description;
    private String title;

    public Ynet(String image, String link, String description, String title) {
        this.image = image;
        this.link = link;
        this.description = description;
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "Ynet{" +
                "image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
