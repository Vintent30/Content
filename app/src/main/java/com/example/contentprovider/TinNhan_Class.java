package com.example.contentprovider;

public class TinNhan_Class {
    private String address;
    private String body;

    public TinNhan_Class(String address, String body) {
        this.address = address;
        this.body = body;
    }

    public TinNhan_Class() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "From: " + address + "\nMessage: " + body;
    }
}
