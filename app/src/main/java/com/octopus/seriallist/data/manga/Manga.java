package com.octopus.seriallist.data.manga;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "manga_table")
public class Manga {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String titleName;

    @ColumnInfo(name = "episodesNumber")
    private int episodesNumber;

    public Manga() {
    }

    public Manga(String titleName, int episodesNumber) {
        this.titleName = titleName;
        this.episodesNumber = episodesNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public int getEpisodesNumber() {
        return episodesNumber;
    }

    public void setEpisodesNumber(int episodesNumber) {
        this.episodesNumber = episodesNumber;
    }
}
