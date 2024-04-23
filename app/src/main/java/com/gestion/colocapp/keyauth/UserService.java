package com.gestion.colocapp.keyauth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("protocol/openid-connect/token")
    Call<UserResponse> login(@Header("Content-Type") String contentType,@Body UserLoginRequest userLoginRequest);

    @POST("user")
    Call<RegistrationResponse> registerUser(@Header("Content-Type") String contentType, @Body UserRegisterRequest userRegisterRequest);
}

