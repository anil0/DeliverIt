package com.example.anilrahman.parceldelivery;

/**
 * Created by anil on 15/01/2017.
 */

public class Collection
{
    private Parcel parcel;
    private String collectionDate;

    public Collection(Parcel parcel, String collectionDate)
    {
        this.parcel = parcel;
        this.collectionDate = collectionDate;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public String getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(String collectionDate) {
        this.collectionDate = collectionDate;
    }
}
