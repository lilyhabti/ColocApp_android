package com.gestion.colocapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gestion.colocapp.R;
import com.gestion.colocapp.data.FlatShareService;
import com.gestion.colocapp.data.RetrofitClient;
import com.gestion.colocapp.pojo.FlatShare;
import com.gestion.colocapp.pojo.User;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlatShareActivity extends AppCompatActivity {

    private TextView descTextView;
    private TextView addressTextView;
    private TextView roomsTextView;
    private TextView occupiedTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flat_share);

        descTextView = findViewById(R.id.desc);
        addressTextView = findViewById(R.id.adress);
        roomsTextView = findViewById(R.id.rooms);
        occupiedTextView = findViewById(R.id.occupied);
        imageView = findViewById(R.id.imageView19);

        findViewById(R.id.goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity when the login button is clicked
                startActivity(new Intent(FlatShareActivity.this, DashProActivity.class));
            }
        });
        // Get the username from the intent
        User user = getIntent().getParcelableExtra("user");

        // Retrofit client setup
        FlatShareService service = RetrofitClient.getRetrofitInstance().create(FlatShareService.class);

        // Call the API to fetch FlatShare data for the specified username
        Call<FlatShare> call = service.getFlatShareByOwnerUsername(user.getUsername());
        call.enqueue(new Callback<FlatShare>() {
            @Override
            public void onResponse(Call<FlatShare> call, Response<FlatShare> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FlatShare flatShare = response.body();
                    // Update UI with FlatShare data
                    String d = "About : " + flatShare.getDescriptionF();
                    String a = "Address : "+ flatShare.getAddress();
                    String n = "Number of rooms is : " + flatShare.getNumberOfRooms();
                    String o = "Number of rooms occupied is : " + flatShare.getNumberOfRoomsOccupied();
                    descTextView.setText(d);
                    addressTextView.setText(a);
                    roomsTextView.setText(n);
                    occupiedTextView.setText(o);

                    // Load image using Picasso
                    Picasso.get().load(flatShare.getFlatPic()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<FlatShare> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
