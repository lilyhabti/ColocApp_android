package com.gestion.colocapp.data;

import com.gestion.colocapp.pojo.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("/api/users")
    Call<User> signUp(@Body User user);

    @GET("/api/users/login")
    Call<User> login(@Query("username") String username, @Query("password") String password);
}

