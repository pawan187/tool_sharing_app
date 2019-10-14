package com.example.demo;

public class item {
    private String id;
    private  String image_title;
    private String image_url;
    private String owner_id;
    private String available;
    private  String description;
    private String pin;
    public item( String imgtit, String imgurl, String ownid, String avail,String pin,String description){
        this.image_title = imgtit;
        this.image_url = imgurl;
        this.owner_id = ownid;
        this.available = avail;
        this.pin = pin;
        this.description = description;
    }
    public item(String id, String imgtit, String imageurl){
        this.image_url = imageurl;
        this.id = id;
        this.image_title = imgtit;
    }
    public item(){

    }
    public void setId(String id){
        this.id = id;
    }
    public void setImage_title(String imgtit){
        this.image_title = imgtit;
    }
    public void setImage_url(String imgurl){
        this.image_url = imgurl;
    }
    public void setOwner_id(String ownid){
        this.owner_id = ownid;
    }
    public void setAvailable(String avail){
        this.available = avail;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId(){
        return  this.id;
    }
    public String getOwner_id(){
        return this.owner_id;
    }
    public String getImage_title(){
        return this.image_title;
    }
    public String getImage_url(){
        return this.image_url;
    }
    public String getAvailable(){
        return this.available;
    }

    public String getDescription() {
        return description;
    }

    public String getPin() {
        return pin;
    }
}
