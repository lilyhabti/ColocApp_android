package com.gestion.colocapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);

        findViewById(R.id.loginbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity when the login button is clicked
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
//        findViewById(R.id.signupbtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Code to navigate to signup activity
//                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
//            }
//        });
//        Toast.makeText(this, "welcome to FlatMingle",Toast.LENGTH_LONG).show();
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}