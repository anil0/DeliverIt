package com.example.anilrahman.parceldelivery;

/**
 * Created by anil on 09/01/2017.
 */

public class User
{
    private String username;
    private String password;
    private boolean isDriver;

    public User()
    {
    }

    public User(String username, String password, boolean isDriver )
    {
        this.username = username;
        this.password = password;
        this.isDriver = isDriver;
    }


    public boolean isDriver()
    {
        return isDriver;
    }


    public void setDriver( boolean driver )
    {
        isDriver = driver;
    }


    public String getUsername()
    {
        return username;
    }


    public void setUsername( String username )
    {
        this.username = username;
    }


    public String getPassword()
    {
        return password;
    }


    public void setPassword( String password )
    {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isDriver=" + isDriver +
                '}';
    }
}
