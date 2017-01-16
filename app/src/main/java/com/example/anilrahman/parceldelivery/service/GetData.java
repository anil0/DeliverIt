package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import com.example.anilrahman.parceldelivery.Parcel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class GetData extends AsyncTask<String, String, String>
{
    public static ArrayList<Parcel> parcels = new ArrayList<>();

    public static ArrayList<Parcel> getArrayList()
    {
        return parcels;
    }

        @Override
        protected String doInBackground(String... params)
        {
            //String uri = "http://10.0.2.2:9998/helloworld";
            String uri = "http://10.0.2.2:9998/parcel";
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
            //builder1.append("Getting data...\n");
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
           // builder.append("\n");

            Parser parser = new Parser();
            parser.parseParcelJson(builder1.toString());

            if (reader != null)
            {
                reader.close();
            }
            con.disconnect();
        }

        @Override
        protected void onPostExecute(String result)
        {
            System.out.println("ENTERED ONPOSTEXECUTE GET DATA");
            System.out.println(result);
        }
}

