package com.example.j2eeassignment2.Models;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private Timestamp orderDate;
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String landmark;
    private String state;
    private String pin;
    private String phone;

    public Order(int id, int userId, Timestamp orderDate, String firstName, String lastName, String city, String street, String landmark, String state, String pin, String phone) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.landmark = landmark;
        this.state = state;
        this.pin = pin;
        this.phone = phone;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }


    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getLandmark() { return landmark; }
    public void setLandmark(String landmark) { this.landmark = landmark; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return "Order [id=" + id + ", userId=" + userId + ", orderDate=" + orderDate + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city + ", street=" + street + ", landmark=" + landmark + ", state=" + state + ", pin=" + pin + ", phone=" + phone + "]";
    }
}
