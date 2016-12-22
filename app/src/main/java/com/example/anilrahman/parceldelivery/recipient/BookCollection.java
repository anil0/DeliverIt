package com.example.anilrahman.parceldelivery.recipient;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.anilrahman.parceldelivery.driver.DeliverParcel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.anilrahman.parceldelivery.recipient.ViewBookedCollections.bookedCollections;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class BookCollection extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_book_collection);

        final ListView listView = (ListView) findViewById(R.id.listViewRecipientBookCollection);

        // Initializing a new String Array
        String[] parcels = new String[]{
                "32 inch TV",
                "Gaming PC Desktop",
                "Dual Pack fans Corsair"

        };

        // Create a List from String Array elements
        final List<Parcel> parcelsList = new ArrayList<>();//Arrays.asList(parcels)

        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","32 inch TV"));
        parcelsList.add(new Parcel("anil","94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK","Gaming PC Desktop"));
        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","Dual Pack fans Corsair"));

        //filtered array list of users parcels
        final List<Parcel> parcelListFiltered = new ArrayList<>();

        for(Parcel parcel : parcelsList)
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
                String text = listView.getItemAtPosition(position).toString();
                Parcel product = parcelListFiltered.get(position); //access object
                //System.out.println(text);
                //System.out.println(attempt.getUsername() + "/////" + attempt.getProductName());

                Intent intent = new Intent(getApplicationContext(), BookCollectionDay.class);
                intent.putExtra("ProductName",product.getProductName());
                startActivity(intent);

            }
        });

        //get back the data from the calender picker and find out the date they chose
        String collectionDate = getIntent().getStringExtra("CollectionDate");
        String productName = getIntent().getStringExtra("ProductName");
        if(collectionDate != null)
        {
            Log.i("Recipient", "The product = " + productName);
            Log.i("Recipient", "The collection date = " + collectionDate);

            Toast.makeText(getApplicationContext(), "Your collection date for " + productName +
                    " is: " + collectionDate, Toast.LENGTH_LONG).show();

            //Here define all your sharedpreferences code with key and value
            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("Product", productName );
            edit.putString("CollectionDate", collectionDate);
            edit.commit();

            //save the shared preferences to my map list for use in another activity
            SharedPreferences preferences = getSharedPreferences("my_prefs", 0);//name , mode
            String product = preferences.getString("Product", "");
            String collectionDate2 = preferences.getString("CollectionDate", "");
            bookedCollections.put(product, collectionDate2);


        }

    }
}
