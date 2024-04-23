package com.gestion.colocapp.keyauth;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("access_token")
    private String access_token;
    @SerializedName("expires_in")
    private int expires_in;
    @SerializedName("refresh_token")
    private String refresh_token;
    @SerializedName("refresh_expires_in")
    private int refresh_expires_in;
    @SerializedName("token_type")
    private String token_type;

    @SerializedName("not-before-policy")
    private int not_before_policy;

    @SerializedName("session_state")
    private String session_state;

    // Constructor, getters, and setters

    public UserResponse(String access_token, int expires_in, String refresh_token, int refresh_expires_in, String token_type, int not_before_policy, String session_state, String scope) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.refresh_expires_in = refresh_expires_in;
        this.token_type = token_type;
        this.not_before_policy = not_before_policy;
        this.session_state = session_state;
        this.scope = scope;
    }

    public int getRefresh_expires_in() {
        return refresh_expires_in;
    }

    public void setRefresh_expires_in(int refresh_expires_in) {
        this.refresh_expires_in = refresh_expires_in;
    }

    public int getNot_before_policy() {
        return not_before_policy;
    }

    public void setNot_before_policy(int not_before_policy) {
        this.not_before_policy = not_before_policy;
    }

    public String getSession_state() {
        return session_state;
    }

    public void setSession_state(String session_state) {
        this.session_state = session_state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @SerializedName("scope")
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
