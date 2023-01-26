package com.octopus.seriallist.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.octopus.seriallist.data.SerialRoomDatabase;
import com.octopus.seriallist.data.dao.SerialDao;
import com.octopus.seriallist.data.entity.Serial;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SerialRepository {
    private SerialDao serialDao;
    private LiveData<List<Serial>> allTitle;
    private static String poster;
    Integer id = -4;

    public void setId(Integer id) {
        this.id = id;
    }

    public SerialRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        serialDao = db.serialDao();
        allTitle = serialDao.getAlphabetizedTitle();
    }

    public LiveData<List<Serial>> getAllTitle() {
        return allTitle;
    }

    public void updatePoster(int serialId, String poster) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.updatePoster(serialId, poster);
        });
    }

    public Integer isEmpty(String title, int season){
        SerialRoomDatabase.databaseWriteExecutor.execute( ()->{
         setId(serialDao.isEmpty(title,season));
        });
        return id;
    }

    public String selectPoster(int serialId) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            this.poster = serialDao.selectPoster(serialId);
        });
        return this.poster;
    }

    public void insert(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.insert(serial);
        });
    }

    public void update(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.update(serial);
        });
    }

    public void delete(Serial serial) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            serialDao.delete(serial);
        });
    }

}
