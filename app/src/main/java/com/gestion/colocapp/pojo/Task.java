package com.gestion.colocapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public class Task implements Parcelable {
    @SerializedName("idTask")
    private Long idTask;

    @SerializedName("descriptionT")
    private String descriptionT;

    @SerializedName("deadline")
    private LocalDateTime deadline;

    // Assurez-vous que les champs assignedTo et flatShareTasks sont également Parcelable si nécessaire

    public Task(String descriptionT, LocalDateTime deadline) {
        this.descriptionT = descriptionT;
        this.deadline = deadline;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public void setDescriptionT(String descriptionT) {
        this.descriptionT = descriptionT;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    protected Task(Parcel in) {
        idTask = in.readLong();
        descriptionT = in.readString();
        // Lisez le champ deadline si nécessaire
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public Long getIdTask() {
        return idTask;
    }

    public String getDescriptionT() {
        return descriptionT;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    // Implémentez Parcelable pour écrire et lire les champs nécessaires

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idTask);
        dest.writeString(descriptionT);
        // Écrivez le champ deadline si nécessaire
    }
}
