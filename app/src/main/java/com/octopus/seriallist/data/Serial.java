package com.octopus.seriallist.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "serial_table")
public class Serial {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "season")
    private int season;

    @ColumnInfo(name = "episodes")
    private int episodes;

    @ColumnInfo(name = "poster")
    private String poster;

    public Serial(String title, int season) {
        this.title = title;
        this.season = season;

    }

    public Serial() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSeason() {
        return season;
    }

    public int getEpisodes() {
        return episodes;
    }

    public String getPoster() {
        return poster;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
