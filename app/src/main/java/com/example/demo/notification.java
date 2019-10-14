package com.example.demo;

public class notification {
    private String id;
    private String userId;
    private String OwnerId;
    private String productId;
    private String productname;
    private String type;
    private String status;
    private String Ownername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public notification(String un, String on, String productId, String pn, String type, String status){
        this.OwnerId = on;
        this.productId = productId;
        this.productname = pn;
        this.userId = un;
        this.type = type;
        this.status = status;
    }

    public notification(String ProductId,String username,String productname){
        this.productId = ProductId;
        this.productname = productname;
        this.OwnerId = username;
    }
    public notification(){
    }
    public String getStatus() {
        return status;
    }

    public String getOwnername() {
        return Ownername;
    }

    public String getProductId() {
        return productId;
    }


    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwnerId() {
        return OwnerId;
    }

    public String getProductname() {
        return productname;
    }

    public String getUserId() {
        return userId;
    }

    public void setOwnerId(String ownername) {
        this.OwnerId = ownername;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setUserId(String username) {
        this.userId = username;
    }
}
