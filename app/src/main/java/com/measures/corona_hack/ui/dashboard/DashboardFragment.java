package com.measures.corona_hack.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.measures.corona_hack.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    EditText updated_peakTime, updated_items;
    Button update;
    FirebaseUser firebaseUser;
    DatabaseReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        updated_items=root.findViewById(R.id.updated_items);
        updated_peakTime=root.findViewById(R.id.updated_peakTime);
        update=root.findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemUpdate=updated_items.getText().toString();
                String timeUpdate=updated_peakTime.getText().toString();
                if(TextUtils.isEmpty(itemUpdate) || TextUtils.isEmpty(timeUpdate))
                    Toast.makeText(getContext(), "Enter both fields", Toast.LENGTH_SHORT).show();

                firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
                ref= FirebaseDatabase.getInstance().getReference("StoreOwners").child(firebaseUser.getUid());
                ref.child("peak_time").setValue(timeUpdate);
                ref.child("items").setValue(itemUpdate);
                Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}