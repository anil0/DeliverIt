package com.example.anilrahman.parceldelivery.driver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;
import com.example.anilrahman.parceldelivery.service.DeleteData;
import com.example.anilrahman.parceldelivery.service.GetData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class DeliverParcel extends AppCompatActivity {

    final static List<Parcel> deliveredParcels = new ArrayList<>();
    final static List<Parcel> parcelsList = new ArrayList<>();
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
            //System.out.println(parcel.toString());
            parcelArrayList.add(parcel);
        }
        GetData.getArrayList().clear();

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

        // Create an ArrayAdapter from List
        final ArrayAdapter<Parcel> arrayAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, parcelArrayList);

        // DataBind ListView with items from ArrayAdapter
        listView.setAdapter(arrayAdapter);

//        GetData getData = new GetData();
//        getData.execute();


        //add item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Toast.makeText(getApplicationContext(),"Item was delivered",Toast.LENGTH_SHORT).show();
                //put the parcel object through a delivery process
                //it was delivered
                deliveredParcels.add(parcelArrayList.remove(position));

                //delete parcel from delivery list once clicked as delivered
                DeleteData deleteData = new DeleteData();
                deleteData.execute();

                arrayAdapter.notifyDataSetChanged();

//                Intent intent = new Intent(getApplicationContext(), DriverMenu.class);
//                startActivity(intent);
            }
        });

    }

    public static List<Parcel> getDeliveredParcels()
    {
        return deliveredParcels;
    }
}
