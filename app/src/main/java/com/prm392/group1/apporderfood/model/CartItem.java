package com.prm392.group1.apporderfood.model;

public class CartItem {

    private String productName;

    private String totalPrice;

    public CartItem(String productName, String totalPrice) {
        this.productName = productName;
        this.totalPrice = totalPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
