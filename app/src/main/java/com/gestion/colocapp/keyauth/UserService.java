package com.gestion.colocapp.keyauth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("protocol/openid-connect/token")
    Call<UserResponse> login(@Body UserLoginRequest userLoginRequest);

    @POST("user")
    Call<RegistrationResponse> registerUser(@Body UserRegisterRequest userRegisterRequest);
}

