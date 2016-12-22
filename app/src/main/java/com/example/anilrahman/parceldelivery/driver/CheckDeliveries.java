package com.example.anilrahman.parceldelivery.driver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class CheckDeliveries extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_check_deliveries);

        ListView listView = (ListView) findViewById(R.id.listViewCheckDeliveries);

        // Initializing a new String Array
        String[] parcels = new String[] {
                "2 Sage Cl, Stoke-on-Trent ST1 3SF, UK",
                "94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK",
                "26 Grafton St, Stoke-on-Trent ST1 2HL, UK",
                "4 Victoria Square, Stoke-on-Trent ST1 4JH, UK",
                "76-80 Sun St, Stoke-on-Trent ST1, UK",
                "2 Morley St, Stoke-on-Trent ST1 4LS, UK",
                "87 Sun St, Stoke-on-Trent ST1, UK",
                "61 Bucknall Old Rd, Stoke-on-Trent ST1 2AQ, UK",
                "44 Watermeadow Grove, Stoke-on-Trent ST1 5GJ, UK",
                "2 Mowbray Walk, Stoke-on-Trent ST1 6JY, UK"
        };

        // Create a List from String Array elements
        final List<Parcel> parcelsList = new ArrayList<>();

        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","32 inch TV"));
        parcelsList.add(new Parcel("anil","94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK","Gaming PC Desktop"));
        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","Dual Pack fans Corsair"));

        // Create an ArrayAdapter from List
        final ArrayAdapter<Parcel> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, parcelsList);

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(arrayAdapter);

    }
}
