package com.octopus.seriallist.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.octopus.seriallist.data.SerialRoomDatabase;
import com.octopus.seriallist.data.dao.EpisodeDao;
import com.octopus.seriallist.data.entity.Episode;

import java.util.List;

public class EpisodeRepository {
    private EpisodeDao episodeDao;
    private LiveData<List<Episode>> allEpisodes;

    public EpisodeRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        episodeDao = db.episodeDao();
        allEpisodes = episodeDao.getAllEpisodes();
    }

    public LiveData<List<Episode>> getAllEpisodes() {
        return allEpisodes;
    }

    public LiveData<List<Episode>> getAllEpisodesById(String id) {
        return episodeDao.loadAllByIds(id);
    }

    public void insert(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.insert(episode);
        });
    }

    public void deleteAll(String id){
        SerialRoomDatabase.databaseWriteExecutor.execute(()->{
            episodeDao.deleteAll(id);
        });
    }

    public void delete(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.delete(episode);
        });
    }

    public void update(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.update(episode);
        });
    }

}
