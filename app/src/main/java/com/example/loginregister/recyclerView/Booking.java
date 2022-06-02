package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Booking recyclerview
public class Booking implements Serializable {


    String name;
    String category;
    String notes;
    String payment;
    String payment_status;
    String phone;
    String email;

    String eventid;

    int bookingid;




    public Booking(int bookingid,String eventid,String name,String category,String notes,String payment,String payment_status,String phone,String email) {
        this.name = name;
        this.category = category;
        this.notes = notes;
        this.payment = payment;
        this.payment_status = payment_status;
        this.phone = phone;
        this.email = email;

        this.eventid = eventid;
        this.bookingid = bookingid;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
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



    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }


}
