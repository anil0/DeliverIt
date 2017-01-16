package com.example.anilrahman.parceldelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anilrahman.parceldelivery.driver.DriverMenu;
import com.example.anilrahman.parceldelivery.recipient.RecipientMenu;
import com.example.anilrahman.parceldelivery.service.GetUsers;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private int id = 0;
    private String username, password;
    private static EditText usernameField, passwordField;
    private Button btnLogin;
    private JSONArray jsonArray;
    private boolean isDriver;
    private ArrayList<User> userArrayList;
    private ArrayList<Parcel> parcelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GetUsers getData = new GetUsers();
        try
        {
            String str1 = new GetUsers().execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        userArrayList = new ArrayList<>();
        for (User user: GetUsers.getArrayList())
        {
           // System.out.println(user.toString());
            userArrayList.add(user);
        }



        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    if(checkWebServiceUsers())
                    {
                        Toast.makeText(getApplicationContext(), "Welcome user you have passed the test", Toast.LENGTH_SHORT).show();

                        //login successful, go to next screen
                        Class intentClass = DriverMenu.class;

                        //if driver show one screen with different options compared to a recipients options
                        if(isDriver)
                        {
                            intentClass = DriverMenu.class;
                        }
                        else
                        {
                            intentClass = RecipientMenu.class;
                        }

                        //go to screen dependant on user
                        Intent intent = new Intent(getApplicationContext(), intentClass);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "You are not a current member", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


    }


//    public void createHardcodedJSONUsers() throws JSONException {
//
//        JSONObject user = new JSONObject();
//
//        try
//        {
//            user.put("id", 0);
//            user.put("username", "anil");
//            user.put("password","test");
//            user.put("isDriver",true);
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//
//        JSONObject user2 = new JSONObject();
//
//        try
//        {
//            user2.put("id", 1);
//            user2.put("username", "test");
//            user2.put("password","test");
//            user2.put("isDriver",false);
//        }
//        catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
//
//        //create array in json format of users, each one containing user object
//        jsonArray = new JSONArray();
//
//        jsonArray.put(user); //first object user
//        jsonArray.put(user2); //second object user
//
////        JSONObject usersObj = new JSONObject(); //new object which is the array itself
////        usersObj.put("Users", jsonArray); //call it Users and pass the objects
////
////        String jsonStr = usersObj.toString(); // string
//
//        //System.out.println("jsonString: " + jsonStr); //display it
//
//    }

    public boolean checkWebServiceUsers()
    {

        for (User user : userArrayList)
        {
            //System.out.println("Username: " + user.getUsername());
            //System.out.println("UsernameField: " + usernameField.getText().toString());
            if(usernameField.getText().toString().equals(user.getUsername()) && passwordField.getText().toString().equals(user.getPassword()))
            {
                isDriver = user.isDriver();
                return true; //is a user so login
            }
        }

        return false;//not a user yet
    }

    public static EditText getUsernameField() {
        return usernameField;
    }
}
