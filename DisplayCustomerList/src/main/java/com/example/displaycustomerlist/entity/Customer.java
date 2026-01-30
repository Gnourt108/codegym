package com.example.displaycustomerlist.entity;

import java.util.Date;

public class Customer {
    private String name;
    private Date date;
    private String address;
    private String img;

    public Customer(String name, Date date, String address, String img) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
