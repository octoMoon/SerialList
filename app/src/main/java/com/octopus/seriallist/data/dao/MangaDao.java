package com.octopus.seriallist.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.octopus.seriallist.data.entity.Manga;
import java.util.List;

@Dao
public interface MangaDao {

    @Query("SELECT * FROM manga_table ORDER BY title ASC")
    LiveData<List<Manga>> getAllEpisodes();

    @Query("SELECT * FROM manga_table WHERE title =(:title)")
    Manga check(String title);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Manga manga);

    @Update
    void update(Manga manga);

    @Delete
    void delete(Manga manga);
}
