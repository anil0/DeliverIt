package com.example.anilrahman.parceldelivery;

/**
 * Created by anilrahman on 05/12/2016.
 */

public class Parcel {
    private String username;
    private String address;
    private String productName;

    public Parcel(String username, String address, String productName) {
        this.username = username;
        this.address = address;
        this.productName = productName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return address + " (" + productName + ")";
    }
}
