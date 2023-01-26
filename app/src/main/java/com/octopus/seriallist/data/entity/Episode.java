package com.octopus.seriallist.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "episode_table")
public class Episode {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title_and_season")
    private String titleAndSeason;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "viewed")
    private boolean viewed;

    public Episode() {
    }

    public Episode(String titleAndSeason, int number, boolean viewed) {
        this.titleAndSeason = titleAndSeason;
        this.number = number;
        this.viewed = viewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleAndSeason() {
        return titleAndSeason;
    }

    public void setTitleAndSeason(String titleAndSeason) {
        this.titleAndSeason = titleAndSeason;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

}
