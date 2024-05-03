package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("idUser")
    private Long idUser;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("profilePic")
    private String profilePic;

    @SerializedName("role")
    private String role;

    // Constructeur, getters et setters ici


    public User(String username, String email, String password, String profilePic, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.role = role;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Méthodes pour Parcelable
    protected User(Parcel in) {
        idUser = in.readLong();
        username = in.readString();
        email = in.readString();
        password = in.readString();
        profilePic = in.readString();
        role = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idUser);
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(profilePic);
        dest.writeString(role);
    }
}
