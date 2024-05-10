package com.gestion.colocapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gestion.colocapp.R;
import com.gestion.colocapp.data.RetrofitClient;
import com.gestion.colocapp.pojo.User;
import com.gestion.colocapp.data.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private ImageView btnLogin;
    private UserService userService;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = getSharedPreferences("my_prefs", MODE_PRIVATE);

        etUsername = findViewById(R.id.editTextText);
        etPassword = findViewById(R.id.editTextTextpassword);
        btnLogin = findViewById(R.id.imageView5);

        userService = RetrofitClient.getRetrofitInstance().create(UserService.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Veuillez saisir votre nom d'utilisateur et votre mot de passe", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(username, password);
                }
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

    private void loginUser(String username, String password) {
        Call<User> call = userService.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();

                    if (user != null) {
                        // Authentification réussie
                        handleLoginSuccess(user);
                    } else {
                        Toast.makeText(LoginActivity.this, "Identifiants invalides", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleLoginSuccess(User user) {

        String userKey = "first_login_" + user.getUsername();

        boolean isFirstLogin = sharedPref.getBoolean(userKey, true);

        // Enregistrer l'état first_login pour cet utilisateur
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(userKey, false);
        editor.apply();

        // Vérifier le rôle de l'utilisateur et le rediriger en conséquence
        if (user.getRole().equals("PROPRIETAIRE")) {
            if (isFirstLogin) {
                // Première connexion du PROPRIETAIRE, rediriger vers add_app
                Intent intent = new Intent(LoginActivity.this, AddAppActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            } else {
                // Rediriger vers dash_pro
                Intent intent = new Intent(LoginActivity.this, DashProActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        } else if (user.getRole().equals("DEMANDEUR")) {
            if (isFirstLogin) {
                // Première connexion du DEMANDEUR, rediriger vers join_app
                startActivity(new Intent(LoginActivity.this, JoinAppActivity.class));
            } else {
                // Rediriger vers dash_dem
                startActivity(new Intent(LoginActivity.this, DashDemActivity.class));
            }
        }
        // Fermer l'activité de connexion pour éviter de revenir en arrière
        finish();
    }
}

