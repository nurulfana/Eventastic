package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Guest recyclerview
public class Venue implements Serializable {

    String bookstatus;
    String dewan;
    String ratings;
    String image;
    String longitude;
    String latitude;
    String search;


    public Venue(String dewan, String bookstatus,String ratings, String image, String longitude, String latitude, String search) {
        this.bookstatus = bookstatus;
        this.dewan = dewan;
        this.image = image;
        this.ratings = ratings;
        this.longitude = longitude;
        this.latitude = latitude;
        this.search = search;


    }



    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }

    public String getDewan() {
        return dewan;
    }

    public void setDewan(String dewan) {
        this.dewan = dewan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }



}
