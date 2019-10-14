package com.example.demo;

public class user {
    private String  id;
    private String username;
    private String pic_url;
    private String dob;
    private long contact;
    private String email;
    private long pin;
    private String address;

    public user(){

    }
    public user( String id, String username, String pic_url, String dob, long contact, long pin, String address){
        this.id  =id;
        this.username = username;
        this.pic_url = pic_url;
        this.dob  = dob;
        this.contact = contact;
        this.email = email;
        this.pin = pin;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public String  getId() {
        return id;
    }

    public long getContact() {
        return contact;
    }

    public long getPin() {
        return pin;
    }

    public String getAddress() {
        return address;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public void setPin(long pin) {
        this.pin = pin;
    }
}
