package com.example.anilrahman.parceldelivery.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anilrahman.parceldelivery.MainActivity;
import com.example.anilrahman.parceldelivery.R;

/**
 * Created by anilrahman on 25/11/2016.
 */

public class RecipientMenu extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_menu_activity);

        Button btnBookCollection = (Button) findViewById(R.id.btnBookCollection);
        btnBookCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookCollection.class);
                startActivity(intent);
            }
        });

        Button btnCollectParcel = (Button) findViewById(R.id.btnCollectParcel);
        btnCollectParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewBookedCollections.class);
                startActivity(intent);
            }
        });

        Button btnTrackParcel = (Button) findViewById(R.id.btnTrackParcel);
        btnTrackParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrackParcelList.class);
                startActivity(intent);
            }
        });

        Button btnLogout = (Button) findViewById(R.id.btnRecipientLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
