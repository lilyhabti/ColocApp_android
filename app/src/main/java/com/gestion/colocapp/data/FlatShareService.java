package com.gestion.colocapp.data;

import com.gestion.colocapp.pojo.FlatShare;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlatShareService {

    @POST("/api/flatshares")
    Call<FlatShare> createFlatShare(@Body FlatShare flatShare, @Query("ownerUsername") String ownerUsername);
    @GET("/api/flatshares/{username}")
    Call<FlatShare> getFlatShareByOwnerUsername(@Path("username") String username);
}