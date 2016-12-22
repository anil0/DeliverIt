package com.example.anilrahman.parceldelivery.recipient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;
import com.example.anilrahman.parceldelivery.driver.DeliverParcel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

/**
 * Created by anilrahman on 05/12/2016.
 */

public class TrackParcel extends AppCompatActivity
{
    TextView textViewTrackParcel;
    boolean isDelivered;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_track_parcel);

        isDelivered = false;
        String productName = getIntent().getStringExtra("ProductName");

        textViewTrackParcel = (TextView) findViewById(R.id.textViewTrackParcel);
        textViewTrackParcel.setText("");

        Gson gson = new Gson();
        String jsonDeliveredParcels = gson.toJson(DeliverParcel.getDeliveredParcels());
        Log.i("TAG","jsonParcels = " + jsonDeliveredParcels);

        Type type = new TypeToken<List<Parcel>>(){}.getType();
        List<Parcel> deliveredParcelsList = gson.fromJson(jsonDeliveredParcels, type);

        //System.out.println("mysout" + deliveredParcelsList);
        for(Parcel p : deliveredParcelsList)
        {
            //System.out.println("indiv " + p);
            if(p.getProductName().equals(productName))
            {
                isDelivered = true;
                break;
            }

        }

        getDeliveryJourney();


    }

    public void getDeliveryJourney() {
        String[] locations = new String[]{
                "stoke", "birmingham", "hanley", "london", "longton"
        };

        Random random = new Random();

        //its been delivered
        if (isDelivered)
        {
            textViewTrackParcel.append("\nParcel was delivered");
        }
        else
        {
            if (random.nextBoolean()) //left
            {
                textViewTrackParcel.append("Parcel has left the Depo\n");
                //has been sent off for delivery
                if (random.nextBoolean()) //then location
                {
                    //where has it been
                    int num = random.nextInt(5);
                    textViewTrackParcel.append(locations[num]);
                }
            }
            else
            {
                textViewTrackParcel.append("\nParcel has not left the Depo");
                //not sent off for delivery as of yet
            }

        }
    }
}
