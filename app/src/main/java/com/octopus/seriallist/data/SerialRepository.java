package com.octopus.seriallist.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class SerialRepository {
    private SerialDao serialDao;
    private LiveData<List<Serial>> allTitle;

    SerialRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        serialDao = db.serialDao();
        allTitle = serialDao.getAlphabetizedTitle();
    }

    LiveData<List<Serial>> getAllTitle() {
        return allTitle;
    }

   void insert(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.insert(serial);
        });
    }

    void delete(Serial serial){
        SerialRoomDatabase.databaseWriteExecutor.execute(() ->{
            serialDao.delete(serial);
        });
    }

}
