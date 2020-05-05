package com.measures.corona_hack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText username, email, password, address, store_name, timings, peak_time, off_day, city, locality, items, special_items;
    DatabaseReference ref;
    FirebaseAuth auth;
    Button register_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth=FirebaseAuth.getInstance();

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        address=findViewById(R.id.address);
        store_name=findViewById(R.id.store_name);
        timings=findViewById(R.id.timings);
        peak_time=findViewById(R.id.peak_time);
        off_day=findViewById(R.id.off_day);
        city=findViewById(R.id.city);
        locality=findViewById(R.id.locality);
        items=findViewById(R.id.items);
        special_items=findViewById(R.id.special_items);

        register_button=findViewById(R.id.register_button);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String txt_user = username.getText().toString();
                String txt_email = email.getText().toString();
                String txt_pass = password.getText().toString();
                String txt_city=city.getText().toString().toUpperCase().trim();
                String txt_local=locality.getText().toString();
                String txt_address=address.getText().toString();
                String txt_items=items.getText().toString();
                String txt_specials=special_items.getText().toString();
                String txt_time=timings.getText().toString();
                String txt_peak=peak_time.getText().toString();
                String txt_off=off_day.getText().toString();
                String txt_store=store_name.getText().toString();

                if (TextUtils.isEmpty(txt_user) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_pass)) {
                    Toast.makeText(Register.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else
                    register(txt_user, txt_email, txt_pass, txt_city, txt_address, txt_items, txt_local, txt_off, txt_peak, txt_store, txt_time, txt_specials);
            }
        });

    }

    private void register(final String username, String email, String password, final String city, final String address, final String items, final String locality, final String off_day, final String peak_time, final String store_name, final String timings, final String special_items) {

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "Done", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = auth.getCurrentUser();

                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            ref = FirebaseDatabase.getInstance().getReference("StoreOwners").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("user_id", userid);
                            hashMap.put("username", username);
                            hashMap.put("city", city);
                            hashMap.put("locality", locality);
                            hashMap.put("address", address);
                            hashMap.put("peak_time", peak_time);
                            hashMap.put("timings", timings);
                            hashMap.put("off_day", off_day);
                            hashMap.put("store_name", store_name);
                            hashMap.put("items", items);
                            hashMap.put("special_items", special_items);

                            ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(Register.this, OwnerActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });}
                    }
                });
    }
}
