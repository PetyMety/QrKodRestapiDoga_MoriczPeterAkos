package com.example.qrkodrestapidoga_moriczpeterakos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieListActivity extends AppCompatActivity {

    private TextView baseUrlTextView;
    private Button fetchMoviesButton;
    private ListView moviesListView;
    private ArrayList<Movie> movieList;
    private MovieAdapter movieAdapter;
    private String baseUrl;

    private Button addMoviesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_movie_list), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        baseUrl = getIntent().getStringExtra("BASE_URL");
        baseUrlTextView = findViewById(R.id.baseUrlTextView);
        fetchMoviesButton = findViewById(R.id.fetcMoviesButton);
        moviesListView = findViewById(R.id.moviesListView);
        addMoviesButton = findViewById(R.id.addMoviesButton);

        RetrofitService apiService = RetrofitClient.getInstance().create(RetrofitService.class);
        baseUrlTextView.setText(baseUrl);
        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(this, movieList);
        moviesListView.setAdapter(movieAdapter);

        fetchMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchMovies(apiService);
            }
        });

        addMoviesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovieListActivity.this, AddMovieActivity.class);
                startActivity(intent);
            }
        });

        moviesListView.setOnItemLongClickListener((parent, view, position, id) -> {
            deleteMovies(movieList.get(position).getId(), apiService);
            return true;
        });
    }

    private void fetchMovies(RetrofitService apiService) {

        apiService.getMovies().enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    movieList.clear();
                    for(Movie movie : response.body()){
                        if(movie.getRating()>=4){
                            movieList.add(movie);
                        }
                    }
                    movieAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MovieListActivity.this, "Nem sikerült betölteni a filmlistát", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.e("MovieListActivity", "Hiba történt a filmek lekérdezésekor: " + t.getMessage());
                Toast.makeText(MovieListActivity.this, "Hiba történt a filmek lekérdezésekor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteMovies(int movieId, RetrofitService apiService) {

        apiService.deleteMovie(movieId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    fetchMovies(apiService);
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MovieListActivity.this, "Hiba történt a filmek törlésekor!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

