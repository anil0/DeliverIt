package com.example.anilrahman.parceldelivery.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

import com.example.anilrahman.parceldelivery.Collection;
import com.example.anilrahman.parceldelivery.Parcel;
import com.example.anilrahman.parceldelivery.R;
import com.example.anilrahman.parceldelivery.service.Parser;
import com.example.anilrahman.parceldelivery.service.PostData;

import org.json.JSONException;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class BookCollectionDay extends AppCompatActivity {
    public static Parcel parcelObj = new Parcel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipient_book_collection_day);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarViewBookCollection);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {

                i1 = i1 + 1;

                //get the product id passed with intent
                String productName = getIntent().getStringExtra("ProductName");
                String username = getIntent().getStringExtra("Username");
                String parcel = getIntent().getStringExtra("Parcel");

                Parser parser = new Parser();
                try
                {
                    parser.parseParcelJson(parcel);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


                Log.i("Recipient (" + productName + ")","Selected Date:\n" +
                        "\t" + i2 + "/" + i1 + "/" + i);

                //Post data new Collection information
                String collectionDate = i2 + "/" + i1 + "/" + i;
                Collection collectionObject = new Collection(parcelObj,collectionDate);
                PostData postData = new PostData();
                postData.execute(collectionObject);
                //

                Intent intent = new Intent(getBaseContext(), BookCollection.class);
                intent.putExtra("ProductName", productName);
                intent.putExtra("CollectionDate", i2 + "/" + i1 + "/" + i);
                //intent.putExtra("ParcelCollection",collectionObject.toString());

                startActivity(intent);

                //reset i1 back to nothing until the calender is selected and sets itself back
                i1 = 0;

            }
        });
    }
}
