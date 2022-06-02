package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Guest recyclerview
public class Guest implements Serializable {

    int guestid;
    String eventid;
    String name;
    String gender;
    String category;
    String quantity;
    String progress;
    String phone;
    String email;
    String notes;



    public Guest(int guestid, String eventid, String name, String gender, String category, String quantity, String progress, String phone, String email, String notes) {
        this.guestid = guestid;
        this.eventid = eventid;
        this.name = name;
        this.gender = gender;
        this.category = category;
        this.quantity = quantity;
        this.progress = progress;
        this.phone = phone;
        this.email = email;
        this.notes = notes;

    }

    public int getGuestid() {
        return guestid;
    }

    public void setGuestid(int guestid) {
        this.guestid = guestid;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }






}
