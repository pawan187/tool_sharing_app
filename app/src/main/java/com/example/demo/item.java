package com.example.demo;

public class item {
    private int id;
    private  String image_title;
    private int image_url;
    private int owner_id;
    private String available;
    public item(int id,String imgtit, int imgurl,int ownid,String avail){
        this.id = id;
        this.image_title = imgtit;
        this.image_url = imgurl;
        this.owner_id = ownid;
        this.available = avail;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setImage_title(String imgtit){
        this.image_title = imgtit;
    }
    public void setImage_url(int imgurl){
        this.image_url = imgurl;
    }
    public void setOwner_id(int ownid){
        this.owner_id = ownid;
    }
    public void setAvailable(String avail){
        this.available = avail;
    }

    public int getId(){
        return  this.id;
    }
    public int getOwner_id(){
        return this.owner_id;
    }
    public String getImage_title(){
        return this.image_title;
    }
    public int getImage_url(){
        return this.image_url;
    }
    public String getAvailable(){
        return this.available;
    }
}
