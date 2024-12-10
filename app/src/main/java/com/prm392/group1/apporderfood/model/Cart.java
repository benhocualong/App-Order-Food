package com.prm392.group1.apporderfood.model;

import java.util.List;

public class Cart {

    private int id;

    private double totalPrice;

    private String username;

    private double totalShip;

    private double totalDiscount;

    private double totalAmount;

    List<CartItem> cartItemList;

    public Cart(int id, double totalPrice, String username, double totalShip, double totalDiscount, double totalAmount, List<CartItem> cartItemList) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.username = username;
        this.totalShip = totalShip;
        this.totalDiscount = totalDiscount;
        this.totalAmount = totalAmount;
        this.cartItemList = cartItemList;
    }

    public double getTotalShip() {
        return totalShip;
    }

    public void setTotalShip(double totalShip) {
        this.totalShip = totalShip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
}
