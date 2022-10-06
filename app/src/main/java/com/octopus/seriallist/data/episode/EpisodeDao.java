package com.octopus.seriallist.data.episode;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EpisodeDao {
    @Query("SELECT * FROM episode_table ORDER BY id ASC")
    LiveData<List<Episode>> getAllEpisodes();

    @Query("SELECT * FROM episode_table WHERE id = :episodeId")
    LiveData<List<Episode>> findById(String episodeId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Episode episode);

    @Query("DELETE FROM episode_table")
    void deleteAll();

    @Delete
    void delete(Episode episode);
}
