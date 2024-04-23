package com.gestion.colocapp.keyauth;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofitLogin = null;
    private static Retrofit retrofitRegister = null;

    private static final String BASE_URL_LOGIN = "http://192.168.0.149:8080/realms/coloc-app/";
    private static final String BASE_URL_REGISTER = "http://192.168.0.149:8081/api/";

    public static UserService getUserServiceForLogin() {
        if (retrofitLogin == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);

            OkHttpClient client = builder.build();
            retrofitLogin = new Retrofit.Builder()
                    .baseUrl(BASE_URL_LOGIN)
                    .client(client)
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
