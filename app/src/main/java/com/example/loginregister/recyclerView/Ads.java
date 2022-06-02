package com.example.loginregister.recyclerView;

import java.io.Serializable;

//Ads recyclerview
public class Ads implements Serializable {

    int ads_id;
    String aname;
    String acategory;
    String astatus;
    String notes;
    String eventid;


    public Ads(int ads_id,String eventid, String aname, String acategory, String astatus,String notes) {
        this.ads_id = ads_id;
        this.eventid = eventid;
        this.aname = aname;
        this.acategory = acategory;
        this.astatus = astatus;
        this.notes = notes;

    }

    public int getAds_id() {
        return ads_id;
    }

    public void setAds_id(int ads_id) {
        this.ads_id = ads_id;
    }


    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAcategory() {
        return acategory;
    }

    public void setAcategory(String acategory) {
        this.acategory = acategory;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }



    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}
