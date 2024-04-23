package com.gestion.colocapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestion.colocapp.keyauth.RetrofitClient;
import com.gestion.colocapp.keyauth.TokenManager;
import com.gestion.colocapp.keyauth.UserResponse;
import com.gestion.colocapp.keyauth.UserLoginRequest;
import com.gestion.colocapp.keyauth.UserService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import okhttp3.Request;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private ImageView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        etUsername = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextTextpassword);
        btnLogin = findViewById(R.id.imageView5);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        findViewById(R.id.textView5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginActivity when the login button is clicked
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        String contentType = "application/x-www-form-urlencoded";
        UserLoginRequest userLoginRequest = new UserLoginRequest(username,password,"password","coloc-app-client","Y3Og7npiX8tUKh5F2O08GKmy9tVuj5rX");

        // Call the login API endpoint
        UserService userService = RetrofitClient.getUserServiceForLogin();
        Call<UserResponse> call = userService.login(contentType,userLoginRequest);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    // Login successful, handle the response
                    UserResponse loginResponse = response.body();
                    if (loginResponse != null) {
                        // Save the access token and refresh token
                        TokenManager.saveTokens(LoginActivity.this, username, loginResponse.getAccess_token(), loginResponse.getRefresh_token());

                        // Redirect to the main activity or any other activity
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish(); // Close the current activity to prevent going back
                    } else {
                        // Response body is empty or null

                        Toast.makeText(LoginActivity.this, "Login failed: Empty response body", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("LoginActivity", "Login request failed: " + response.message());
                    Log.d("LoginActivity", "Request headers: " + call.request().headers());
                    Log.d("LoginActivity", "Request body: " + call.request().body().toString());
                    Toast.makeText(LoginActivity.this, "Login failed: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                // Login request failed
                Log.e("LoginActivity", "Login request failed", t);
                Toast.makeText(LoginActivity.this, "Login request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
