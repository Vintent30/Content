package com.example.contentprovider;

public class DanhBa_Class {
    private String name;
    private String phone;

    public DanhBa_Class(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Tên: " + name + "\nSố điện thoại: " + phone;
    }
}
