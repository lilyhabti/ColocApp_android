package com.gestion.colocapp.keyauth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitLogin = null;
    private static Retrofit retrofitRegister = null;

    private static final String BASE_URL_LOGIN = "http://localhost:8080/realms/coloc-app/";
    private static final String BASE_URL_REGISTER = "http://localhost:8081/api/";

    public static UserService getUserServiceForLogin() {
        if (retrofitLogin == null) {
            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(BASE_URL_LOGIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitLogin.create(UserService.class);
    }

    public static UserService getUserServiceForRegister() {
        if (retrofitRegister == null) {
            retrofitRegister = new Retrofit.Builder()
                    .baseUrl(BASE_URL_REGISTER)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitRegister.create(UserService.class);
    }
}
