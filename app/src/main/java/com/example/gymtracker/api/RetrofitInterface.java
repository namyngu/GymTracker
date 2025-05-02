package com.example.gymtracker.api;

import com.example.gymtracker.BuildConfig;
import com.example.gymtracker.entity.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    String API_KEY = BuildConfig.API_KEY;
    @Headers({
            "X-Api-Key: " + API_KEY
    })
    @GET("v1/exercises?muscle=")
    Call<List<Exercise>> searchMuscles(
            @Query("muscle")
            String muscle
    );

    @GET("v1/exercises?name=")
    Call<List<Exercise>> searchName(@Query("name")String name);
}
