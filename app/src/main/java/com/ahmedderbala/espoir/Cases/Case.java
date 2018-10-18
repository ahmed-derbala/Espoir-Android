package com.ahmedderbala.espoir.Cases;

public class Case {
    private int id;
    private String title;
    private String shortDescription;
    private String longDescription;
    private String thumbnail;
    private String author;
    private String governorate;
    private String city;
    private String place;

    public Case() {
    }

    public Case(String title, String shortDescription, String longDescription, String thumbnail, String author, String governorate, String city, String place) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.thumbnail = thumbnail;
        this.author = author;
        this.governorate = governorate;
        this.city = city;
        this.place = place;
    }

    public Case(int id, String title, String shortDescription, String longDescription, String thumbnail, String author, String governorate, String city, String place) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.thumbnail = thumbnail;
        this.author = author;
        this.governorate = governorate;
        this.city = city;
        this.place = place;
    }

    public Case(String title, String shortDescription) {
        this.title = title;
        this.shortDescription = shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
