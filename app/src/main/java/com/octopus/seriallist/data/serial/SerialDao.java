package com.octopus.seriallist.data.serial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SerialDao {
    @Query("SELECT * FROM serial_table ORDER BY title ASC")
    LiveData<List<Serial>> getAlphabetizedTitle();

    @Query("UPDATE serial_table SET poster = :poster WHERE id IN (:serialId)")
    void updatePoster(int serialId, String poster);

    @Query("SELECT poster FROM serial_table WHERE id IN (:serialId)")
    String selectPoster (int serialId);

    @Query("SELECT id FROM serial_table WHERE title = :title AND season = :season ")
    int isEmpty(String title, int season);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Serial serial);

    @Query("DELETE FROM serial_table")
    void deleteAll();

    @Delete
    void delete(Serial serial);

    @Update
    void update(Serial serial);
}
