package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class DeleteData extends AsyncTask<String, String, String>
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

                deleteData(url, builder);

                return builder.toString(); //this builder is the result passed to the onPostExecute which is printing it
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return e.getMessage();
            }
            //return builder.toString();
        }

        private void deleteData(URL url, StringBuilder builder) throws Exception
        {
            builder.append("Deleting data...\n");
            BufferedReader reader = null;

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("DELETE");
            con.connect();

            builder.append("Deleted Record\n");
            con.getResponseMessage();
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

