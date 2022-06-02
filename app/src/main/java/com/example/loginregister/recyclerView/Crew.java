

package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Guest recyclerview
public class Crew implements Serializable {


    int crew_id;
    String event_id;
    String name;
    String category;
    String quantity;
    String progress;
    String phone;
    String email;
    String notes;

    public Crew(int crew_id , String event_id, String name, String category, String quantity, String progress, String phone, String email, String notes) {
        this.crew_id = crew_id;
        this.event_id = event_id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.progress = progress;
        this.phone = phone;
        this.email = email;
        this.notes = notes;

    }


    public int getCrew_id() {
        return crew_id;
    }

    public void setCrew_id(int crew_id) {
        this.crew_id = crew_id;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
