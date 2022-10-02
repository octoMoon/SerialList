package com.octopus.seriallist.data.serial;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SerialDao {
    @Query("SELECT * FROM serial_table ORDER BY title ASC")
    LiveData<List<Serial>> getAlphabetizedTitle();

    @Query("SELECT * FROM serial_table WHERE id = :serialId")
    Serial findById(int serialId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Serial serial);

    @Query("DELETE FROM serial_table")
    void deleteAll();

    @Delete
    void delete(Serial serial);
}
