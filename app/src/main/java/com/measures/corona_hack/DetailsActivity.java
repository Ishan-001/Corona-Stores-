package com.measures.corona_hack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView username, store_name, items, address, peak_time, off_day, special_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        username=findViewById(R.id.username);
        store_name=findViewById(R.id.store_name);
        address=findViewById(R.id.address);
        items=findViewById(R.id.items);
        peak_time=findViewById(R.id.peak_time);
        off_day=findViewById(R.id.off_day);
        special_items=findViewById(R.id.special_items);

        Intent intent=getIntent();

        username.setText(intent.getExtras().getString("Owner name"));
        store_name.setText(intent.getExtras().getString("Store name"));
        address.setText(intent.getExtras().getString("Address"));
        items.setText(intent.getExtras().getString("Items"));
        peak_time.setText(intent.getExtras().getString("Peak time"));
        off_day.setText(intent.getExtras().getString("Off day"));
        special_items.setText(intent.getExtras().getString("Special items"));

    }
}
