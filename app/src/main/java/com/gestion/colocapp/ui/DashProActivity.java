package com.gestion.colocapp.ui;

import static com.gestion.colocapp.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.gestion.colocapp.R;
import com.gestion.colocapp.pojo.User;

public class DashProActivity extends AppCompatActivity {

    ImageView home;
    ImageView profile;
    ImageView expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_pro);
        home = findViewById(R.id.home);
        profile = findViewById(R.id.monpro);
        expenses = findViewById(R.id.expenses);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashProActivity.this, FlatShareActivity.class);
                User user = getIntent().getParcelableExtra("user");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashProActivity.this, ProfileActivity.class);
                User user = getIntent().getParcelableExtra("user");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashProActivity.this, ExpensesListActivity.class);
                User user = getIntent().getParcelableExtra("user");
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


    }
}