package com.example.navigationdrawer;

public class Customer {
    int id;
    String name,amount,address,phone,date,status,date_stamp,description;
    byte[] imgByte;

    public Customer(int id, String name, String amount, String address, String phone, String date, String status, String date_stamp, String description, byte[] imgByte) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.status = status;
        this.date_stamp = date_stamp;
        this.description = description;
        this.imgByte = imgByte;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate_stamp() {
        return date_stamp;
    }

    public void setDate_stamp(String date_stamp) {
        this.date_stamp = date_stamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImgByte() {
        return imgByte;
    }

    public void setImgByte(byte[] imgByte) {
        this.imgByte = imgByte;
    }
}

