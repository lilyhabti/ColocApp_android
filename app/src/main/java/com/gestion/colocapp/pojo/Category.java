package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Category implements Parcelable {
    @SerializedName("idCat")
    private Long idCat;

    @SerializedName("name")
    private String name;

    // Assurez-vous que la liste d'Expense est également Parcelable si vous en avez besoin

    public Category(String name) {
        this.name = name;
    }

    protected Category(Parcel in) {
        idCat = in.readLong();
        name = in.readString();
        // Lisez la liste d'Expense si nécessaire
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public void setIdCat(Long idCat) {
        this.idCat = idCat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCat() {
        return idCat;
    }

    public String getName() {
        return name;
    }

    // Implémentez Parcelable pour écrire et lire les champs nécessaires

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idCat);
        dest.writeString(name);
        // Écrivez la liste d'Expense si nécessaire
    }
}
