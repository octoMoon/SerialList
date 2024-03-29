package com.octopus.seriallist.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.octopus.seriallist.data.entity.Episode;

import java.util.List;

@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM episode_table ORDER BY id ASC")
    LiveData<List<Episode>> getAllEpisodes();

    @Query("SELECT * FROM episode_table WHERE title_and_season IN (:titleAndSeason) ORDER BY number ASC")
    LiveData<List<Episode>>loadAllByIds(String titleAndSeason);

    @Query("DELETE FROM episode_table WHERE title_and_season IN (:titleAndSeason)")
    void deleteAll(String titleAndSeason);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Episode episode);

    @Query("DELETE FROM episode_table")
    void deleteAll();

    @Update
    void update(Episode episode);

    @Delete
    void delete(Episode episode);
}
