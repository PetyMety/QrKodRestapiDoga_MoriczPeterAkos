package com.example.qrkodrestapidoga_moriczpeterakos;

public class Movie {
    private int id;
    private String director;
    private int duration;
    private float rating;
    private String category;
    private int year;

    public Movie(String director, int duration, float rating, String category, int year) {
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.category = category;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
