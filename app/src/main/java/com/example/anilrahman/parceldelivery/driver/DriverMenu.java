package com.example.anilrahman.parceldelivery.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anilrahman.parceldelivery.MainActivity;
import com.example.anilrahman.parceldelivery.R;

import java.sql.Driver;

/**
 * Created by anilrahman on 25/11/2016.
 */
public class DriverMenu extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_menu_activity);

        Button btnCheckDeliveries = (Button) findViewById(R.id.btnCheckDeliveries);
        btnCheckDeliveries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CheckDeliveries.class);
                startActivity(intent);
            }
        });

        Button btnDeliverParcel = (Button) findViewById(R.id.btnDeliverParcel);
        btnDeliverParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DeliverParcel.class);
                startActivity(intent);
            }
        });

        Button btnDriverLogout = (Button) findViewById(R.id.btnDriverLogout);
        btnDriverLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
