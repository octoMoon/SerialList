package com.octopus.seriallist.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SerialDao {
    @Query("SELECT * FROM serial_table ORDER BY title ASC")
    LiveData<List<Serial>> getAlphabetizedTitle();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Serial serial);

    @Query("DELETE FROM serial_table")
    void deleteAll();
}