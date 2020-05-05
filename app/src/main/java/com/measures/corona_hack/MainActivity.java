package com.measures.corona_hack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView storeOwner, customer, search;
    ImageView corona_image;
    FirebaseUser firebaseUser;
    EditText city_input;

    @Override
    protected void onStart(){
        super.onStart();

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){
            Intent intent=new Intent(MainActivity.this, OwnerActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        corona_image = findViewById(R.id.corona);
        storeOwner=findViewById(R.id.store_owner);
        customer=findViewById(R.id.customer);
        city_input=findViewById(R.id.city_input);
        search=findViewById(R.id.search_button);

        Animation animation=AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        animation.setDuration(4000);

        corona_image.startAnimation(animation);

        storeOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setVisibility(View.VISIBLE);
                city_input.setVisibility(View.VISIBLE);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city_name=city_input.getText().toString().toUpperCase().trim();
                Intent intent=new Intent(MainActivity.this, CustomerActivity.class);
                intent.putExtra("City", city_name);
                startActivity(intent);
            }
        });
    }
}

