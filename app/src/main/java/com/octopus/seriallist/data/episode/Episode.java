package com.octopus.seriallist.data.episode;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episode_table")
public class Episode {


    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "viewed")
    private boolean viewed;

    public Episode() {
    }

    public Episode(@NonNull String id, int number, boolean viewed) {
        this.id = id;
        this.number = number;
        this.viewed = viewed;
    }

    public Episode(@NonNull String id, int number) {
        this.id = id;
        this.number = number;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean getViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
}
