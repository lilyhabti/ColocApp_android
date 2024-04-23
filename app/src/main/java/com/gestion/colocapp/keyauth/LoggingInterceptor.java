package com.gestion.colocapp.keyauth;

import android.util.Log;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (request.body() != null) {
            // Log request body
            String requestBody = request.body().toString();
            Log.d("LoginActivity", "Request body: " + requestBody);
        }

        Response response = chain.proceed(request);

        if (response.body() != null) {
            // Log response body
            String responseBody = response.body().string();
            Log.d("LoginActivity", "Response body: " + responseBody);
        }

        return response;
    }
}