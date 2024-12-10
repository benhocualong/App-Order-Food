package com.prm392.group1.apporderfood.model;

public class CartItem {


    private int id;

    private String productName;

    private String productImage;

    private int quantity;

    private double productOrderPrice;

    private double productPrice;

    private int cartId;


    public CartItem(int id, String productName, String productImage, int quantity, double productOrderPrice, double productPrice, int cartId) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.quantity = quantity;
        this.productOrderPrice = productOrderPrice;
        this.productPrice = productPrice;
        this.cartId = cartId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getProductOrderPrice() {
        return productOrderPrice;
    }

    public void setProductOrderPrice(double productOrderPrice) {
        this.productOrderPrice = productOrderPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
