package com.example.anilrahman.parceldelivery.service;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class GetData extends AsyncTask<String, String, String>
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

                getData(url, builder);
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

        private void getData(URL url, StringBuilder builder) throws Exception
        {
            System.out.println("ENTERED GETDATA...");
            builder.append("Getting data...\n");
            BufferedReader reader = null;

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestMethod("GET");

            Permission permission = con.getPermission();

            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                builder.append(line + "\n");
            }
            builder.append("\n");

            if (reader != null)
            {
                reader.close();
            }
            con.disconnect();
        }

        @Override
        protected void onPostExecute(String result)
        {
            System.out.println("ENTERED ONPOSTEXECUTE");
            System.out.println(result);
        }
}

