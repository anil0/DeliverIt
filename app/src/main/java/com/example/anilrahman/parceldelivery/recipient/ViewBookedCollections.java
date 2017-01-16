package com.example.anilrahman.parceldelivery.recipient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anilrahman.parceldelivery.Item;
import com.example.anilrahman.parceldelivery.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class ViewBookedCollections extends AppCompatActivity {
    static Map<String, String> bookedCollections = new HashMap<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_view_booked_collections);

        ListView listView = (ListView) findViewById(R.id.listViewViewBookedCollections);

        // Create a List from String Array elements
        final List<Item> parcelsList = new ArrayList<>();

        //loop through shared preferences map items to know which items were booked for collection
        for (Map.Entry<String, String> entry : bookedCollections.entrySet())
        {
            //add items to list
            parcelsList.add(new Item(entry.getKey(), entry.getValue()));
        }

        // Create an ArrayAdapter from List of Item objects
        final ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, parcelsList);

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(arrayAdapter);

//        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent intent = new Intent( getApplicationContext(), EditCollection.class );
////                intent.putExtra( "ProductName", product.getProductName() );
////                intent.putExtra("Username", product.getUsername());
////                intent.putExtra("Parcel",jsonParcelObject.toString());
//                startActivity( intent );
//            }
//        });

    }

}
