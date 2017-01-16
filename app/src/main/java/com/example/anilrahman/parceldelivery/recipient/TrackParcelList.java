package com.example.anilrahman.parceldelivery.recipient;

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

import com.example.anilrahman.parceldelivery.MainActivity;
import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;
import com.example.anilrahman.parceldelivery.service.GetData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by anilrahman on 05/12/2016.
 */

public class TrackParcelList extends AppCompatActivity {
    final static List<Parcel> parcelsList = new ArrayList<>();
    final List<Parcel> parcelListFiltered = new ArrayList<>();
    final static List<Parcel> parcelArrayList = new ArrayList<>();
    static
    {
        //Get parcels from WebService instead of hardcoded
        //GetData getParcelData = new GetData();
        try
        {
            String str = new GetData().execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        // Starts task > webservice > gets the data > parses data > static array

        for (Parcel parcel: GetData.getArrayList())
        {
            System.out.println(parcel.toString());
            parcelArrayList.add(parcel);
        }
        GetData.getArrayList().clear();

        parcelsList.add(new Parcel("test", "2 Sage Cl, Stoke-on-Trent ST1 3SF, UK", "32 inch TV"));
        parcelsList.add(new Parcel("anil", "94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK", "Gaming PC Desktop"));
        parcelsList.add(new Parcel("test", "2 Sage Cl, Stoke-on-Trent ST1 3SF, UK", "Dual Pack fans Corsair"));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_track_parcel_list);

        ListView listView = (ListView) findViewById(R.id.listViewTrackParcelsList);

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


        for(Parcel parcel : parcelArrayList)
        {
            if(parcel.getUsername().equals(MainActivity.getUsernameField().getText().toString()))
            {
                //show only those parcels
                parcelListFiltered.add(parcel);
            }
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<Parcel> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, parcelListFiltered);

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(arrayAdapter);

        //add item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(),"Item was clicked",Toast.LENGTH_SHORT).show();
                Log.i("Recipient","Item was clicked");

                //text value of the item clicked
                //String text = listView.getItemAtPosition(position).toString();
                Parcel product = parcelListFiltered.get(position); //access object
                //System.out.println(text);
                //System.out.println(attempt.getUsername() + "/////" + attempt.getProductName());

                Intent intent = new Intent(getApplicationContext(), TrackParcel.class);
                intent.putExtra("ProductName",product.getProductName());
                startActivity(intent);

            }
        });
    }
}
