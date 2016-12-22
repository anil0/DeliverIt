package com.example.anilrahman.parceldelivery;

/**
 * Created by anilrahman on 28/11/2016.
 */
public class DataDTO
{
    private String data = "";

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "DataDTO{" + "data=" + data + '}';
    }
}

