package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FlatShare implements Parcelable {

    @SerializedName("idFlat")
    private Long idFlat;

    @SerializedName("descriptionF")
    private String descriptionF;

    @SerializedName("address")
    private String address;

    @SerializedName("numberOfRooms")
    private int numberOfRooms;

    @SerializedName("numberOfRoomsOccupied")
    private int numberOfRoomsOccupied;

    @SerializedName("flatPic")
    private String flatPic;

    @SerializedName("owner")
    private User owner;

    @SerializedName("roomates")
    private List<User> roomates = new ArrayList<>();
    @SerializedName("expenses")
    private List<Expense> expenses = new ArrayList<>();
    @SerializedName("categories")
    private List<Category> categories = new ArrayList<>();

    // Ajoutez des constructeurs, des getters et des setters selon vos besoins


    public FlatShare(String descriptionF, String address, int numberOfRooms, int numberOfRoomsOccupied, String flatPic) {
        this.descriptionF = descriptionF;
        this.address = address;
        this.numberOfRooms = numberOfRooms;
        this.numberOfRoomsOccupied = numberOfRoomsOccupied;
        this.flatPic = flatPic;
    }

    public Long getIdFlat() {
        return idFlat;
    }

    public void setIdFlat(Long idFlat) {
        this.idFlat = idFlat;
    }

    public String getDescriptionF() {
        return descriptionF;
    }

    public void setDescriptionF(String descriptionF) {
        this.descriptionF = descriptionF;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfRoomsOccupied() {
        return numberOfRoomsOccupied;
    }

    public void setNumberOfRoomsOccupied(int numberOfRoomsOccupied) {
        this.numberOfRoomsOccupied = numberOfRoomsOccupied;
    }

    public String getFlatPic() {
        return flatPic;
    }

    public void setFlatPic(String flatPic) {
        this.flatPic = flatPic;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getRoomates() {
        return roomates;
    }

    public void setRoomates(List<User> roomates) {
        this.roomates = roomates;
    }

    protected FlatShare(Parcel in) {
        if (in.readByte() == 0) {
            idFlat = null;
        } else {
            idFlat = in.readLong();
        }
        descriptionF = in.readString();
        address = in.readString();
        numberOfRooms = in.readInt();
        numberOfRoomsOccupied = in.readInt();
        flatPic = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        roomates = in.createTypedArrayList(User.CREATOR);
    }

    public static final Creator<FlatShare> CREATOR = new Creator<FlatShare>() {
        @Override
        public FlatShare createFromParcel(Parcel in) {
            return new FlatShare(in);
        }

        @Override
        public FlatShare[] newArray(int size) {
            return new FlatShare[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (idFlat == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(idFlat);
        }
        dest.writeString(descriptionF);
        dest.writeString(address);
        dest.writeInt(numberOfRooms);
        dest.writeInt(numberOfRoomsOccupied);
        dest.writeString(flatPic);
        dest.writeParcelable(owner, flags);
        dest.writeTypedList(roomates);
    }
}

