package com.octopus.seriallist.data.manga;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface MangaDao {

    @Query("SELECT * FROM manga_table ORDER BY id ASC")
    LiveData<List<Manga>> getAllEpisodes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Manga manga);

    @Update
    void update(Manga manga);

    @Delete
    void delete(Manga manga);
}
