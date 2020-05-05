package com.measures.corona_hack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Store_Owners> owners;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setTitle("Stores");

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        owners=new ArrayList<>();

        Intent intent=getIntent();
        final String city=intent.getExtras().getString("City");

        ref=FirebaseDatabase.getInstance().getReference("StoreOwners");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                owners.clear();
                String size="";

                for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                    Store_Owners owner = snapshot.getValue(Store_Owners.class);

                    if (owner.getCity().equals(city)) {
                        owners.add(owner);
                        size=Integer.toString(owners.size());

                    }
                }
                Toast.makeText(CustomerActivity.this, size+" stores found." , Toast.LENGTH_SHORT).show();

                OwnerAdapter ownerAdapter=new OwnerAdapter(getApplicationContext(), owners);
                recyclerView.setAdapter(ownerAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
