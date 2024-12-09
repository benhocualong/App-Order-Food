package com.prm392.group1.apporderfood.model;

public class CartItem {


    private int id;

    private String productName;

    private String productImage;

    private int quantity;

    private int productOrderPrice;

    private int productPrice;

    private int cartId;

    public CartItem(int id, String productName, String productImage, int quantity, int productOrderPrice, int productPrice, int cartId) {
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

    public int getProductOrderPrice() {
        return productOrderPrice;
    }

    public void setProductOrderPrice(int productOrderPrice) {
        this.productOrderPrice = productOrderPrice;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
