package com.gestion.colocapp.keyauth;

import com.google.gson.annotations.SerializedName;

public class UserRegisterRequest {

    @SerializedName("userName")
    private String username;
    @SerializedName("emailId")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("roleName")
    private String role; // Will be set based on user selection

    // Constructor, getters, and setters


    public UserRegisterRequest(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
