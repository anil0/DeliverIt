package com.example.anilrahman.parceldelivery.service;

import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import static com.example.anilrahman.parceldelivery.recipient.BookCollectionDay.parcelObj;
import static com.example.anilrahman.parceldelivery.service.GetData.parcels;
import static com.example.anilrahman.parceldelivery.service.GetUsers.users;

/**
 * Created by anilrahman on 12/12/2016.
 */

public class Parser
{

    public void parseJson(String s) throws JSONException
    {
        final Object tempJsonObject = new JSONTokener( s ).nextValue();

        if( tempJsonObject instanceof JSONArray)
        {
            parseArray( s );
        }

        if( tempJsonObject instanceof JSONObject)
        {
            JSONObject jsonObject = new JSONObject();
            parseUser( jsonObject );
        }
    }

    public void parseArray(String s) throws JSONException
    {
        final JSONArray jsonArray = new JSONArray( s );
        for (int i = 0; i <jsonArray.length(); i++)
        {
            final JSONObject jsonObject = jsonArray.getJSONObject( i );
            users.add(parseUser( jsonObject ));
        }
    }


    public User parseUser(JSONObject jsonObject) throws JSONException
    {
        final User user = new User();
        user.setUsername( jsonObject.getString( "username" ) );
        user.setPassword( jsonObject.getString( "password" ) );
        user.setDriver( jsonObject.getBoolean( "driver" ) );

        return user;
    }

    public void parseParcelJson(String s) throws JSONException
    {
        final Object tempJsonObject = new JSONTokener( s ).nextValue();

        if( tempJsonObject instanceof JSONArray)
        {
            parseParcelArray( s );
        }

        if( tempJsonObject instanceof JSONObject)
        {
            JSONObject jsonObject = new JSONObject(tempJsonObject.toString());
            parseParcel( jsonObject );
        }
    }

    public void parseParcelArray(String s) throws JSONException
    {
        final JSONArray jsonArray = new JSONArray( s );
        for (int i = 0; i <jsonArray.length(); i++)
        {
            final JSONObject jsonObject = jsonArray.getJSONObject( i );
            parcels.add(parseParcel( jsonObject ));
        }
    }

    public Parcel parseParcel(JSONObject jsonObject) throws JSONException
    {
        final Parcel parcel = new Parcel();
        parcel.setUsername( jsonObject.getString( "username" ) );
        parcel.setAddress( jsonObject.getString( "address" ) );
        parcel.setProductName( jsonObject.getString( "productName" ) );

        parcelObj = parcel;

        return parcel;
    }
}
