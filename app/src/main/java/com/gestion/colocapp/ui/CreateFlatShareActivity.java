package com.gestion.colocapp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gestion.colocapp.R;
import com.gestion.colocapp.data.FlatShareService;
import com.gestion.colocapp.data.RetrofitClient;
import com.gestion.colocapp.pojo.FlatShare;
import com.gestion.colocapp.pojo.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFlatShareActivity extends AppCompatActivity {

    private EditText editTextDescription;
    private EditText editTextAddress;
    private EditText editTextNumRooms;
    private EditText editTextNumRoomsOccupied;
    private ImageView floatingActionButton;
    private ImageView imageView5;
    private ImageView imageView;

    // Code pour sélectionner une image
    private static final int PICK_IMAGE_REQUEST = 1;

    // Variables pour stocker l'image sélectionnée
    private String base64Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flat_share);

        editTextDescription = findViewById(R.id.editTextText);
        editTextAddress = findViewById(R.id.editTextEmail);
        editTextNumRooms = findViewById(R.id.editTextTextpassword);
        editTextNumRoomsOccupied = findViewById(R.id.editTextTextpasswordagain);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        imageView5 = findViewById(R.id.imageView5);
        imageView = findViewById(R.id.imageView8);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer la FlatShare
                createFlatShare();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Obtenir l'URI de l'image sélectionnée
            Uri selectedImageUri = data.getData();

            // Utiliser Glide pour charger et afficher l'image dans votre ImageView
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView);

            // Convertir l'image en String avec Base64
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                base64Image = encodeImage(bitmap); // Stocker l'image encodée en base64 dans base64Image
                Toast.makeText(this, "Image enregistrée avec succès en tant que String Base64", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private String encodeImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }


    private void createFlatShare() {
        String description = editTextDescription.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();
        int numRooms = Integer.parseInt(editTextNumRooms.getText().toString().trim());
        int numRoomsOccupied = Integer.parseInt(editTextNumRoomsOccupied.getText().toString().trim());

        // Vérifier que toutes les informations sont saisies
        if (description.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir toutes les informations", Toast.LENGTH_SHORT).show();
            return;
        }

        // Créer une instance de FlatShare avec les données saisies et l'image convertie en base64
        FlatShare flatShare = new FlatShare(description, address, numRooms, numRoomsOccupied, base64Image);

        // Récupérer le nom d'utilisateur de l'intent (si vous l'avez passé depuis l'activité précédente)
        // String ownerUsername = getIntent().getStringExtra("username");
        User user = getIntent().getParcelableExtra("user");

        // Appeler l'API pour créer la FlatShare
        FlatShareService flatShareService = RetrofitClient.getRetrofitInstance().create(FlatShareService.class);
        Call<FlatShare> call = flatShareService.createFlatShare(flatShare, user.getUsername());
        call.enqueue(new Callback<FlatShare>() {
            @Override
            public void onResponse(Call<FlatShare> call, Response<FlatShare> response) {
                if (response.isSuccessful()) {
                    // Rediriger vers DashProActivity avec l'utilisateur et la FlatShare créée
                    Intent intent = new Intent(CreateFlatShareActivity.this, DashProActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("flatShare", response.body());
                    startActivity(intent);
                    finish(); // Fermer cette activité pour éviter de revenir en arrière
                } else {
                    Toast.makeText(CreateFlatShareActivity.this, "Erreur lors de la création de la FlatShare", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FlatShare> call, Throwable t) {
                Toast.makeText(CreateFlatShareActivity.this, "Erreur de connexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
