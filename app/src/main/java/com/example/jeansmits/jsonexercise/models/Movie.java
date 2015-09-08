package com.example.jeansmits.jsonexercise.models;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
        import java.util.Map;

import retrofit.RestAdapter;

public class Movie {

    @Expose private Integer id;
    @Expose private String title;
    @Expose private Integer length;
    @Expose private Double rating;
    @Expose private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}