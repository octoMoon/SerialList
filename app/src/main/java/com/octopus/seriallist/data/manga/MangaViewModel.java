package com.octopus.seriallist.data.manga;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.octopus.seriallist.data.serial.Serial;
import com.octopus.seriallist.data.serial.SerialRepository;

import java.util.List;

public class MangaViewModel extends AndroidViewModel {

    private MangaRepository repository;
    private LiveData<List<Manga>> allEpisodes;

    public MangaViewModel(@NonNull Application application) {
        super(application);
        repository = new MangaRepository(application);
        allEpisodes = repository.getAllEpisodes();
    }

    public LiveData<List<Manga>> getAllEpisodes() {
        return allEpisodes;
    }

    public void insert(Manga manga) {
        repository.insert(manga);
    }

    public void update(Manga manga){repository.update(manga);}

    public void delete(Manga manga) {
        repository.delete(manga);
    }
}
