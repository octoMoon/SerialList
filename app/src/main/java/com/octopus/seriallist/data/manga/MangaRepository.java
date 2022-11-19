package com.octopus.seriallist.data.manga;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.octopus.seriallist.data.SerialRoomDatabase;

import java.util.List;

public class MangaRepository {
    private MangaDao mangaDao;
    private LiveData<List<Manga>> allEpisodes;

    public MangaRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        mangaDao = db.mangaDao();
        allEpisodes = mangaDao.getAllEpisodes();
    }

    LiveData<List<Manga>> getAllEpisodes() {
        return allEpisodes;
    }

    void insert(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.insert(manga);
        });
    }

    void update(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.update(manga);
        });
    }

    void delete(Manga manga) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            mangaDao.delete(manga);
        });
    }
}
