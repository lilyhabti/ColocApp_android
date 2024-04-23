package com.gestion.colocapp.keyauth;

import com.google.gson.annotations.SerializedName;

public class UserLoginRequest {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("grant_type")
    private String grant_type;
    @SerializedName("client_id")
    private String client_id;
    @SerializedName("client_secret")
    private String client_secret;

    // Constructor, getters, and setters


    public UserLoginRequest(String username, String password, String grant_type, String client_id, String client_secret) {
        this.username = username;
        this.password = password;
        this.grant_type = grant_type;
        this.client_id = client_id;
        this.client_secret = client_secret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }
}
