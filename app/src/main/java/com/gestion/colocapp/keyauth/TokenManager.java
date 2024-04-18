package com.gestion.colocapp.keyauth;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private static final String PREF_NAME = "TokenPrefs";

    public static void saveTokens(Context context, String username, String accessToken, String refreshToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username + "_access_token", accessToken);
        editor.putString(username + "_refresh_token", refreshToken);
        editor.apply();
    }

    public static String getAccessToken(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(username + "_access_token", null);
    }

    public static String getRefreshToken(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(username + "_refresh_token", null);
    }

    public static void clearTokens(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(username + "_access_token");
        editor.remove(username + "_refresh_token");
        editor.apply();
    }
}
