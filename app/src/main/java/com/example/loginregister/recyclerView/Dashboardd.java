package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Guest recyclerview
public class Dashboardd implements Serializable {

    int row_booking_paid;
    int row_booking_notpaid;
    int row_guest_pending;
    int row_guest_sent;
    int row_crew_pending;
    int row_crew_sent;
    int row_ads_unconfirmed;
    int row_ads_confirmed;


    public Dashboardd(int row_booking_paid, int row_booking_notpaid, int row_guest_pending, int row_guest_sent, int row_crew_pending, int row_crew_sent, int row_ads_unconfirmed, int row_ads_confirmed) {

        this.row_booking_paid = row_booking_paid;
        this.row_booking_notpaid = row_booking_notpaid;
        this.row_guest_pending = row_guest_pending;
        this.row_guest_sent = row_guest_sent;
        this.row_crew_pending = row_crew_pending;
        this.row_crew_sent = row_crew_sent;
        this.row_ads_unconfirmed = row_ads_unconfirmed;
        this.row_ads_confirmed = row_ads_confirmed;

    }


    public int getRow_booking_paid() {
        return row_booking_paid;
    }

    public void setRow_booking_paid(int row_booking_paid) {
        this.row_booking_paid = row_booking_paid;
    }

    public int getRow_booking_notpaid() {
        return row_booking_notpaid;
    }

    public void setRow_booking_notpaid(int row_booking_notpaid) {
        this.row_booking_notpaid = row_booking_notpaid;
    }

    public int getRow_guest_pending() {
        return row_guest_pending;
    }

    public void setRow_guest_pending(int row_guest_pending) {
        this.row_guest_pending = row_guest_pending;
    }

    public int getRow_guest_sent() {
        return row_guest_sent;
    }

    public void setRow_guest_sent(int row_guest_sent) {
        this.row_guest_sent = row_guest_sent;
    }

    public int getRow_crew_pending() {
        return row_crew_pending;
    }

    public void setRow_crew_pending(int row_crew_pending) {
        this.row_crew_pending = row_crew_pending;
    }

    public int getRow_crew_sent() {
        return row_crew_sent;
    }

    public void setRow_crew_sent(int row_crew_sent) {
        this.row_crew_sent = row_crew_sent;
    }

    public int getRow_ads_unconfirmed() {
        return row_ads_unconfirmed;
    }

    public void setRow_ads_unconfirmed(int row_ads_unconfirmed) {
        this.row_ads_unconfirmed = row_ads_unconfirmed;
    }

    public int getRow_ads_confirmed() {
        return row_ads_confirmed;
    }

    public void setRow_ads_confirmed(int row_ads_confirmed) {
        this.row_ads_confirmed = row_ads_confirmed;
    }


}
