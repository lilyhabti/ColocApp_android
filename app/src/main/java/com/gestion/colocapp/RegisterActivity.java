package com.gestion.colocapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestion.colocapp.keyauth.RegistrationResponse;
import com.gestion.colocapp.keyauth.RetrofitClient;
import com.gestion.colocapp.keyauth.UserRegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etRePassword;
    private RadioButton rbHomeowner, rbSharedLiving;
    private ImageView btnRegisterImage;

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

        btnRegisterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String username = etUsername.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String rePassword = etRePassword.getText().toString().trim();

        if (!password.equals(rePassword)) {
            // Passwords don't match
            Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
            return;
        }

        String role = rbSharedLiving.isChecked() ? "DEMANDEUR" : "PROPRIETAIRE";
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest(username,email,password,role);

        // Proceed with registration
        // Call your registration API endpoint here
        RetrofitClient.getUserServiceForRegister().registerUser(userRegisterRequest)
                .enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                        if (response.isSuccessful()) {
                            // Registration successful
                            Toast.makeText(RegisterActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                            // Redirect to login activity
                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish(); // Close the current activity to prevent going back
                        } else {
                            // Registration failed
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {
                        // Network error
                        Toast.makeText(RegisterActivity.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}