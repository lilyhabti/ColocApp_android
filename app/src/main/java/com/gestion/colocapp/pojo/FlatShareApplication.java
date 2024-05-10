package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class FlatShareApplication implements Parcelable {
    @SerializedName("idApp")
    private Long idApp;

    @SerializedName("status")
    private ApplicationStatus status;

    // Assurez-vous que les champs applicant et flatShare sont également Parcelable si nécessaire

    public FlatShareApplication(ApplicationStatus status) {
        this.status = status;
    }

    protected FlatShareApplication(Parcel in) {
        idApp = in.readLong();
        // Lisez le champ status si nécessaire
    }

    public static final Creator<FlatShareApplication> CREATOR = new Creator<FlatShareApplication>() {
        @Override
        public FlatShareApplication createFromParcel(Parcel in) {
            return new FlatShareApplication(in);
        }

        @Override
        public FlatShareApplication[] newArray(int size) {
            return new FlatShareApplication[size];
        }
    };

    public Long getIdApp() {
        return idApp;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setIdApp(Long idApp) {
        this.idApp = idApp;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    // Implémentez Parcelable pour écrire et lire les champs nécessaires

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idApp);
        // Écrivez le champ status si nécessaire
    }
}
