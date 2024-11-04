package com.example.contentprovider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Call_Class {
    private String number;
    private String type;
    private Date date; // Change type from String to Date
    private String duration;

    public Call_Class() {
    }

    public Call_Class(String duration, Date date, String type, String number) {
        this.duration = duration;
        this.date = date;
        this.type = type;
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
    private String getCallTypeLabel() {
        switch (type) {
            case "1":
                return "Cuộc gọi đến";
            case "2":
                return "Cuộc gọi đi";
            case "3":
                return "Cuộc gọi nhỡ";
            case "4":
                return "Thư thoại";
            case "5":
                return "Cuộc gọi bị từ chối";
            case "6":
                return "Cuộc gọi bị chặn";
            case "7":
                return "Cuộc gọi trả lời từ thiết bị khác";
            default:
                return "Không xác định";
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Format to show only date
        return "Số điện thoại: " + number + "\n" +
                "Loại cuộc gọi: " + getCallTypeLabel() + "\n" +
                "Ngày gọi: " + sdf.format(date) + "\n" + // Use formatted date
                "Thời lượng: " + duration;
    }
}
