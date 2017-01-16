package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import com.example.anilrahman.parceldelivery.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by anil on 09/01/2017.
 */

public class GetUsers extends AsyncTask<String, String, String>
{
    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getArrayList()
    {
        return users;
    }

    @Override
    protected String doInBackground(String... params)
    {

        String uri = "http://10.0.2.2:9998/helloworld";
        StringBuilder builder = new StringBuilder();

        System.out.println("ENTERED DO IN BACKGROUND");
        try
        {
            URL url = new URL(uri);
            getData(url, builder);

            return builder.toString(); //this builder is the result passed to the onPostExecute which is printing it
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        //return builder.toString();
    }

    private void getData(URL url, StringBuilder builder1) throws Exception
    {
        System.out.println("ENTERED GETDATA...");
        //StringBuilder builder1 = new StringBuilder();
        BufferedReader reader = null;

        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("GET");

        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line = "";
        while ((line = reader.readLine()) != null)
        {
            builder1.append(line);
        }

        Parser parser = new Parser();
        parser.parseJson(builder1.toString());

        if (reader != null)
        {
            reader.close();
        }
        con.disconnect();
    }

//    private void parseJson(String s) throws JSONException
//    {
//        final Object tempJsonObject = new JSONTokener( s ).nextValue();
//
//        if( tempJsonObject instanceof JSONArray )
//        {
//           parseArray( s );
//        }
//
//        if( tempJsonObject instanceof  JSONObject )
//        {
//            JSONObject jsonObject = new JSONObject();
//            parseUser( jsonObject );
//        }
//    }
//
//    void parseArray(String s) throws JSONException
//    {
//        final JSONArray jsonArray = new JSONArray( s );
//        for (int i = 0; i <jsonArray.length(); i++)
//        {
//            final JSONObject jsonObject = jsonArray.getJSONObject( i );
//            users.add(parseUser( jsonObject ));
//        }
//    }
//
//
//    User parseUser(JSONObject jsonObject) throws JSONException
//    {
//        final User user = new User();
//        user.setUsername( jsonObject.getString( "username" ) );
//        user.setPassword( jsonObject.getString( "password" ) );
//        user.setDriver( jsonObject.getBoolean( "driver" ) );
//
//        return user;
//    }

    @Override
    protected void onPostExecute(String result)
    {
        System.out.println("ENTERED ONPOSTEXECUTE");
        System.out.println(result);

    }
}

