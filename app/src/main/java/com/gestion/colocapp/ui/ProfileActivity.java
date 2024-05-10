package com.gestion.colocapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gestion.colocapp.R;
import com.gestion.colocapp.pojo.User;

public class ProfileActivity extends AppCompatActivity {

    ImageView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        log = findViewById(R.id.logout);

        User user = getIntent().getParcelableExtra("user");

        // Remplir les champs d'affichage avec les données de l'utilisateur
        if (user != null) {
            TextView usernameTextView = findViewById(R.id.usernameid);
            TextView username = findViewById(R.id.userdisplay);
            TextView emailTextView = findViewById(R.id.emailtext);

            usernameTextView.setText(user.getUsername());
            username.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
        }
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}