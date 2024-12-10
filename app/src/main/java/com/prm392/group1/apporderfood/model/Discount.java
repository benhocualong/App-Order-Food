package com.prm392.group1.apporderfood.model;

import java.util.Date;

public class Discount {

    private int id;
    private String name;
    private double percentage;
    private Date expirationDate;


    public Discount(int id, String name, double percentage, Date expirationDate) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
