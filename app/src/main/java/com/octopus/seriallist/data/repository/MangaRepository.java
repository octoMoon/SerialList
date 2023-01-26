package com.octopus.seriallist.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.octopus.seriallist.data.SerialRoomDatabase;
import com.octopus.seriallist.data.dao.MangaDao;
import com.octopus.seriallist.data.entity.Manga;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MangaRepository {
    private MangaDao mangaDao;
    private LiveData<List<Manga>> allEpisodes;
    private Manga manga;

    public MangaRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        mangaDao = db.mangaDao();
        allEpisodes = mangaDao.getAllEpisodes();
    }

    public Manga check(String title){
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            manga = mangaDao.check(title);
        });
        return this.manga;
    }

    public LiveData<List<Manga>> getAllEpisodes() {
        return allEpisodes;
    }

    public void insert(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.insert(manga);
        });
    }

    public void update(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.update(manga);
        });
    }

    public void delete(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.delete(manga);
        });
    }
}
