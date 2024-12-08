package com.prm392.group1.apporderfood.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String address;
    private String phone;
    private String mail;

    // Constructor
    public User(int id, String username, String password, String role, String name, String address, String phone, String mail) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
}
