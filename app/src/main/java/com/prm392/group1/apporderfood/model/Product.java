package com.prm392.group1.apporderfood.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private CharSequence price;
    private int image; // ID của ảnh sản phẩm;
    private String category;

    public Product(int id, String name, String description, CharSequence price, int image, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CharSequence getPrice() {
        return price;
    }

    public void setPrice(CharSequence price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
