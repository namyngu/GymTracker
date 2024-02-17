package com.example.gymtracker.api;

import com.example.gymtracker.entity.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitInterface {
    String API_KEY = "kkGgqUB0YLOWl54DkASCww==0k3RUZafGRHHaHVs";
    @Headers({
            "X-Api-Key: " + API_KEY
    })
    @GET("v1/exercises")
    Call<List<Exercise>> getExercises(@Query("filter") String filter, @Query("keyword")String keyword);
}
