package com.example.Bai6.Book;

public class Book {
    private String id;
    private String longDescription;
    private String shortDescription;
    private Integer cost;


    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
