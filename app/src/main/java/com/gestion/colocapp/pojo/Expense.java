package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Expense implements Parcelable {
    @SerializedName("idExp")
    private Long idExp;

    @SerializedName("descriptionE")
    private String descriptionE;

    @SerializedName("amount")
    private double amount;

    @SerializedName("receipt")
    private String receipt;

    // Assurez-vous que les champs category et flatShareExpenses sont également Parcelable si nécessaire

    public Expense(String descriptionE, double amount, String receipt) {
        this.descriptionE = descriptionE;
        this.amount = amount;
        this.receipt = receipt;
    }

    public void setIdExp(Long idExp) {
        this.idExp = idExp;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    protected Expense(Parcel in) {
        idExp = in.readLong();
        descriptionE = in.readString();
        amount = in.readDouble();
        receipt = in.readString();
        // Lisez les champs category et flatShareExpenses si nécessaire
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public Long getIdExp() {
        return idExp;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public double getAmount() {
        return amount;
    }

    public String getReceipt() {
        return receipt;
    }

    // Implémentez Parcelable pour écrire et lire les champs nécessaires

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idExp);
        dest.writeString(descriptionE);
        dest.writeDouble(amount);
        dest.writeString(receipt);
        // Écrivez les champs category et flatShareExpenses si nécessaire
    }
}
