package com.gestion.colocapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.gestion.colocapp.R;
import com.gestion.colocapp.data.RetrofitClient;
import com.gestion.colocapp.pojo.User;
import com.gestion.colocapp.data.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etRePassword;
    private RadioButton rbHomeowner, rbSharedLiving;
    private ImageView btnRegisterImage;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize views
        etUsername = findViewById(R.id.editTextText);
        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextTextpassword);
        etRePassword = findViewById(R.id.editTextTextpasswordagain);
        rbHomeowner = findViewById(R.id.radioButtonOwner);
        rbSharedLiving = findViewById(R.id.radioButtonJoin);
        btnRegisterImage = findViewById(R.id.imageView5);

        // Initialize Retrofit service
        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);

        btnRegisterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignUp();
            }
        });

        findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity when the login button is clicked
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void performSignUp() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String rePassword = etRePassword.getText().toString().trim();
        String role = rbHomeowner.isChecked() ? "PROPRIETAIRE" : "DEMANDEUR";

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(rePassword)) {
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Assuming you have a method in UserService for signing up
        Call<User> call = userService.signUp(new User(username, email, password, "url", role));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("RegistrationError", "Error: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Registration failed failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
