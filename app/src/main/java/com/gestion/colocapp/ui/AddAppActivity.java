package com.gestion.colocapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gestion.colocapp.R;
import com.gestion.colocapp.pojo.User;

public class AddAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_app);
        User user = getIntent().getParcelableExtra("user");
        findViewById(R.id.addApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAppActivity.this, CreateFlatShareActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}