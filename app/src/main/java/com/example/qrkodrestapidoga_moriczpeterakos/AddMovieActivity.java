package com.example.qrkodrestapidoga_moriczpeterakos;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends AppCompatActivity {
    private EditText directorEditText, durationEditText, ratingEditText, categoryEditText, yearEditText;
    private Button addButton;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_add_movie), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        baseUrl = getIntent().getStringExtra("BASE_URL");
        directorEditText = findViewById(R.id.directorEditText);
        durationEditText = findViewById(R.id.durationEditText);
        categoryEditText = findViewById(R.id.categoryEditText)
        ratingEditText = findViewById(R.id.ratingEditText);
        yearEditText = findViewById(R.id.yearEditText);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(view -> addMovie());

    }

    private void addMovie() {
        String director = directorEditText.getText().toString();
        int duration = Integer.parseInt(durationEditText.getText().toString());
        float rating = Float.parseFloat(ratingEditText.getText().toString());
        String category = categoryEditText.getText().toString();
        int year = Integer.parseInt(yearEditText.getText().toString());

        Movie newMovie = new Movie(director, duration, rating, category, year);
        RetrofitService apiService = RetrofitClient.getInstance(baseUrl).create(RetrofitService.class);
        apiService.addMovie(newMovie).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()){
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(AddMovieActivity.this, "Hiba történt a film hozzáadása közben", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
