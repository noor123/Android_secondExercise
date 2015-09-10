package com.example.jeansmits.jsonexercise.models;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private int favoriteNumber;

    public Person(){}

    public Person(String firstName, String lastName, int favoriteNumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteNumber = favoriteNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }
}
