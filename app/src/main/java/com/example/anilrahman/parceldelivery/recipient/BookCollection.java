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
import com.example.anilrahman.parceldelivery.service.GetData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.anilrahman.parceldelivery.recipient.ViewBookedCollections.bookedCollections;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class BookCollection extends AppCompatActivity
{

    //Map<String, String> checkDates = new HashMap<>();

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.recipient_book_collection );

        final ListView listView = (ListView) findViewById( R.id.listViewRecipientBookCollection );

        // Initializing a new String Array
        String[] parcels = new String[] { "32 inch TV", "Gaming PC Desktop", "Dual Pack fans Corsair" };

        // Create a List from String Array elements
//        final List<Parcel> parcelsList = new ArrayList<>();//Arrays.asList(parcels)
//
//        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","32 inch TV"));
//        parcelsList.add(new Parcel("anil","94 Upper Hillchurch St, Stoke-on-Trent ST1 2HG, UK","Gaming PC Desktop"));
//        parcelsList.add(new Parcel("test","2 Sage Cl, Stoke-on-Trent ST1 3SF, UK","Dual Pack fans Corsair"));

        final List<Parcel> parcelArrayList = new ArrayList<>();
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


        //filtered array list of users parcels
        final List<Parcel> parcelListFiltered = new ArrayList<>();

        for( Parcel parcel : parcelArrayList )
        {
            if( parcel.getUsername().equals( MainActivity.getUsernameField().getText().toString() ) )
            {
                //show only those parcels
                parcelListFiltered.add( parcel );
            }
        }

        // Create an ArrayAdapter from List
        final ArrayAdapter<Parcel> arrayAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1,
                parcelListFiltered );

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter( arrayAdapter );

        //add item click listener
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id )
            {

                Toast.makeText( getApplicationContext(), "Item was clicked", Toast.LENGTH_SHORT ).show();
                Log.i( "Recipient", "Item was clicked" );

                //text value of the item clicked
                String text = listView.getItemAtPosition( position ).toString();
                Parcel product = parcelListFiltered.get( position ); //access object
                //System.out.println(text);
                //System.out.println(attempt.getUsername() + "/////" + attempt.getProductName());
                JSONObject jsonParcelObject = new JSONObject();
                try
                {
                    //jsonParcelObject.put("parcel",product);
                    jsonParcelObject.put("username",product.getUsername());
                    jsonParcelObject.put("address",product.getAddress());
                    jsonParcelObject.put("productName",product.getProductName());
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                Intent intent = new Intent( getApplicationContext(), BookCollectionDay.class );
                intent.putExtra( "ProductName", product.getProductName() );
                intent.putExtra("Username", product.getUsername());
                intent.putExtra("Parcel",jsonParcelObject.toString());
                startActivity( intent );

            }
        } );

        //get back the data from the calender picker and find out the date they chose
        String collectionDate = getIntent().getStringExtra( "CollectionDate" );
        String productName = getIntent().getStringExtra( "ProductName" );

        if( collectionDate != null )
        {
            //do PUT here... if date is different from previous date for that parcel //currently just overrides the previous date selected and updates
//            if(productName.equals(productPrevName) && collectionDate.equals(collectionPrevDate))
//            {
//                System.out.println("THE DATE IS THE SAME IS AS BEFORE");
//            }
//            else
//            {
//                System.out.println("THE DATE IS DIFFERENT NOW");
//            }

            Log.i( "Recipient", "The product = " + productName );
            Log.i( "Recipient", "The collection date = " + collectionDate );

            Toast.makeText( getApplicationContext(), "Your collection date for " + productName + " is: " + collectionDate,
                    Toast.LENGTH_LONG ).show();

            //Here define all your sharedpreferences code with key and value
            SharedPreferences prefs = getSharedPreferences( "my_prefs", MODE_PRIVATE );
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString( "Product", productName );
            edit.putString( "CollectionDate", collectionDate );
            edit.commit();

            //save the shared preferences to my map list for use in another activity
            SharedPreferences preferences = getSharedPreferences( "my_prefs", 0 );//name , mode
            String product = preferences.getString( "Product", "" );
            String collectionDate2 = preferences.getString( "CollectionDate", "" );
            bookedCollections.put( product, collectionDate2 );
        }

    }
}
