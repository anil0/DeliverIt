package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class PostData extends AsyncTask<String, String, String>
{
        @Override
        protected String doInBackground(String... params)
        {
            //String uri = "http://10.0.2.2:9998/helloworld";
            String uri = "http://10.4.151.23:9998/helloworld";
            StringBuilder builder = new StringBuilder();

            System.out.println("ENTERED DO IN BACKGROUND");
            try
            {
                URL url = new URL(uri);

                postData(url, builder);
//                postData(url, builder);//d is data object
//                putData(url, builder);
//                deleteData(url, builder);

                return builder.toString(); //this builder is the result passed to the onPostExecute which is printing it
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return e.getMessage();
            }
            //return builder.toString();
        }

        private void postData(URL url, StringBuilder builder) throws Exception
        {
            builder.append("Posting data...\n");

            //create a new json object to post and format it
            JSONObject obj = new JSONObject();
            obj.put("data", "Hello, how are you?");
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

