package com.octopus.seriallist.data.episode;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.octopus.seriallist.data.serial.Serial;
import com.octopus.seriallist.data.serial.SerialRoomDatabase;

import java.util.List;

public class EpisodeRepository {
    private EpisodeDao episodeDao;
    private LiveData<List<Episode>> allEpisodes;

    EpisodeRepository(Application application) {
        SerialRoomDatabase db = SerialRoomDatabase.getDatabase(application);
        episodeDao = db.episodeDao();
        allEpisodes = episodeDao.getAllEpisodes();
    }

    LiveData<List<Episode>> getAllEpisodes() {
        return allEpisodes;
    }

    LiveData<List<Episode>> getAllEpisodesById(String id) {
        return episodeDao.loadAllByIds(id);
    }

    void insert(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.insert(episode);
        });
    }

    void delete(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.delete(episode);
        });
    }

    void update(Episode episode) {
        SerialRoomDatabase.databaseWriteExecutor.execute(() -> {
            episodeDao.update(episode);
        });
    }

}
