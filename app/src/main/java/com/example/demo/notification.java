package com.example.demo;

public class notification {
    private String username;
    private String Ownername;
    private String productname;
    public notification(String un,String on, String pn){
        this.Ownername = on;
        this.productname = pn;
        this.username = un;
    }

    public String getOwnername() {
        return Ownername;
    }

    public String getProductname() {
        return productname;
    }

    public String getUsername() {
        return username;
    }

    public void setOwnername(String ownername) {
        this.Ownername = ownername;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
