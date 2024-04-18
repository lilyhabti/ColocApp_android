package com.gestion.colocapp.keyauth;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }
}