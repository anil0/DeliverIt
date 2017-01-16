package com.example.anilrahman.parceldelivery;

/**
 * Created by anilrahman on 02/12/2016.
 */

//An item is the product that is being put through to collection, this is used for the ViewBookedCollections
public class Item {
    private String productName;
    private String collectionDate;

    public Item(String productName, String collectionDate) {
        this.productName = productName;
        this.collectionDate = collectionDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public String toString() {
        return productName + " ("+collectionDate+")";
    }
}
