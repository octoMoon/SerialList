package com.octopus.seriallist.data.episode;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.octopus.seriallist.data.serial.Serial;

import java.util.List;

public class EpisodeViewModel extends AndroidViewModel {
    private EpisodeRepository repository;
    private LiveData<List<Episode>> allEpisodes;


    public EpisodeViewModel(Application application) {
        super(application);
        repository = new EpisodeRepository(application);
        allEpisodes = repository.getAllEpisodes();

    }

    public LiveData<List<Episode>> getAllEpisodes() {
        return allEpisodes;
    }


    public void insert(Episode episode) {
        repository.insert(episode);
    }

    public void delete(Episode episode) {
        repository.delete(episode);
    }
}
