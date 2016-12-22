package com.example.anilrahman.parceldelivery.recipient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anilrahman.parceldelivery.R;

/**
 * Created by anilrahman on 02/12/2016.
 */

public class BookCollectionDay extends AppCompatActivity {
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

                Log.i("Recipient (" + productName + ")","Selected Date:\n" +
                        "\t" + i2 + "/" + i1 + "/" + i);

                Intent intent = new Intent(getBaseContext(), BookCollection.class);
                intent.putExtra("ProductName", productName);
                intent.putExtra("CollectionDate", i2 + "/" + i1 + "/" + i);
                startActivity(intent);

                //reset i1 back to nothing until the calender is selected and sets itself back
                i1 = 0;

            }
        });
    }
}
