package com.example.moviecollector;

public class MovieRatings {
    private String id;
    private String title;
    private int rating;
    private String imageURL;

    public MovieRatings(String id, String title, String imageURL) {
        this.id = id;
        this.title = title;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
