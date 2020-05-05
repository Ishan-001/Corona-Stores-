package com.measures.corona_hack.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.measures.corona_hack.R;
import com.measures.corona_hack.Store_Owners;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    TextView username, store_name, timings, address, peak_time, off_day, city, locality, special_items;
    DatabaseReference ref;
    FirebaseUser firebaseUser;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        username=root.findViewById(R.id.username);
        store_name=root.findViewById(R.id.store_name);
        address=root.findViewById(R.id.address);
        timings=root.findViewById(R.id.timings);
        peak_time=root.findViewById(R.id.peak_time);
        off_day=root.findViewById(R.id.off_day);
        city=root.findViewById(R.id.city);
        locality=root.findViewById(R.id.locality);
        special_items=root.findViewById(R.id.special_items);

        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        ref= FirebaseDatabase.getInstance().getReference("StoreOwners").child(firebaseUser.getUid());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Store_Owners owner=dataSnapshot.getValue(Store_Owners.class);

                username.setText(owner.getUsername());
                city.setText(owner.getCity());
                locality.setText(owner.getLocality());
                store_name.setText(owner.getStore_name());
                address.setText(owner.getAddress());
                timings.setText(owner.getTimings());
                peak_time.setText(owner.getPeak_time());
                off_day.setText(owner.getOff_day());
                special_items.setText(owner.getSpecial_items());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;


    }
}