package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import com.example.anilrahman.parceldelivery.Collection;

import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class PostData extends AsyncTask<Collection, String, String>
{
        @Override
        protected String doInBackground(Collection... params)
        {
            String uri = "http://10.0.2.2:9998/parcel";
            StringBuilder builder = new StringBuilder();

            System.out.println("ENTERED DO IN BACKGROUND");
            try
            {
                URL url = new URL(uri);

                postData(url, builder, params[0]);

                return null;//builder.toString(); //this builder is the result passed to the onPostExecute which is printing it
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;//e.getMessage();
            }
        }

        private void postData(URL url, StringBuilder builder, Collection collectionObj) throws Exception
        {
            builder.append("Posting data...\n");

            //create a new json object to post and format it
            JSONObject obj = new JSONObject();
            obj.put("ParcelName",collectionObj.getParcel().getProductName());
            obj.put("Username",collectionObj.getParcel().getUsername());
            obj.put("Address",collectionObj.getParcel().getAddress());
            obj.put("CollectionDate",collectionObj.getCollectionDate());

            String jsonStr = obj.toString(2);
            builder.append(jsonStr + "\n");

            //set this as a post request
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            //output the json string
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
            out.write(jsonStr);
            out.flush();
            out.close();
            builder.append("Response code: " + con.getResponseCode() + "\n\n");
            con.disconnect();
        }

        @Override
        protected void onPostExecute(String result)
        {
            System.out.println("ENTERED ONPOSTEXECUTE");
            System.out.println(result);
        }
}

