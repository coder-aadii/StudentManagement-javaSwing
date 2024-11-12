package com.manage_student;

public class Student {
    private int id;
    private String name;
    private String phone;
    private String city;

    // Constructor
    public Student(int id, String name, String phone, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Getter for Phone
    public String getPhone() {
        return phone;
    }

    // Getter for City
    public String getCity() {
        return city;
    }

    // Setters (optional, if needed)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
