package com.example.qrkodrestapidoga_moriczpeterakos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("movies")
    Call<List<Movie>> getMovies(@Query("baseUrl") String baseUrl);

    @DELETE("movies/{id}")
    Call<Void> deleteMovie(@Path("id") int id);

    @POST("movies")
    Call<Movie> addMovie(@Body Movie movie);
}
