package com.example.moviecollector;

public class Movie {

    private int id;
    private String title;
    private String director;
    private String actors;
    private String review;
    private int year;
    private int rating;
    private boolean favourite;

    public Movie(int id, String title, String director, String actors, String review, int year, int rating, boolean favourite) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.actors = actors;
        this.review = review;
        this.year = year;
        this.rating = rating;
        this.favourite = favourite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
