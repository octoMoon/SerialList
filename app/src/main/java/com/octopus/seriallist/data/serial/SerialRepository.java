package com.octopus.seriallist.data.serial;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.octopus.seriallist.data.SerialRoomDatabase;

import java.util.List;

public class SerialRepository {
    private SerialDao serialDao;
    private LiveData<List<Serial>> allTitle;
    private static String poster;
    private int id;

    SerialRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        serialDao = db.serialDao();
        allTitle = serialDao.getAlphabetizedTitle();


    }

    LiveData<List<Serial>> getAllTitle() {
        return allTitle;
    }

    void updatePoster(int serialId, String poster) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.updatePoster(serialId, poster);
        });
    }

    int isEmpty(String title, int season){
        SerialRoomDatabase.databaseWriteExecutor.execute(()->{
            this.id = serialDao.isEmpty(title,season);
        });
        return id;
    }

    String selectPoster(int serialId) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            this.poster = serialDao.selectPoster(serialId);
        });
        return this.poster;
    }

    void insert(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.insert(serial);
        });
    }

    void update(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.update(serial);
        });
    }

    void delete(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.delete(serial);
        });
    }

}
