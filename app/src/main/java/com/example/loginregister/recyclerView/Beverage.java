package com.example.loginregister.recyclerView;
//create event recyclerview
public class Beverage {
    String name;

    int id;

//    int image;
    public Beverage(String name, int id) {
        this.name = name;
        this.id = id;
      //  this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getImage() {
//        return image;
//    }
//
//    public void setImage(int image) {
//        this.image = image;
//    }
}
