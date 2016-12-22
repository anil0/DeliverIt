package com.example.anilrahman.parceldelivery.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;
import com.example.anilrahman.parceldelivery.recipient.BookCollectionDay;
import com.example.anilrahman.parceldelivery.service.GetData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Type;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class DeliverParcel extends AppCompatActivity {

    final static List<Parcel> deliveredParcels = new ArrayList<>();
    final static List<Parcel> parcelsList = new ArrayList<>();
    static
    {
        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","32 inch TV"));
        parcelsList.add(new Parcel("anil","94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK","Gaming PC Desktop"));
        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","Dual Pack fans Corsair"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_deliver_parcel);

        ListView listView = (ListView) findViewById(R.id.listViewDeliverParcel);

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




        //Get webservice json data and parse it into the array

        // Create a List from String Array elements
        //final List<String> parcelsList = new ArrayList<>(Arrays.asList(parcels));

        // Create an ArrayAdapter from List
        final ArrayAdapter<Parcel> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, parcelsList);

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(arrayAdapter);

        GetData getData = new GetData();
        getData.execute();


        //add item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(),"Item was delivered",Toast.LENGTH_SHORT).show();
                //put the parcel object through a delivery process
                //it was delivered
                deliveredParcels.add(parcelsList.remove(position));

                arrayAdapter.notifyDataSetChanged();

//                Intent intent = new Intent(getApplicationContext(), DriverMenu.class);
//                startActivity(intent);


            }

        });



    }

    public static List<Parcel> getDeliveredParcels() {
        return deliveredParcels;
    }
}
