package com.octopus.seriallist.data.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.octopus.seriallist.data.entity.Manga;
import com.octopus.seriallist.data.repository.MangaRepository;

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

    public Manga check(String title) {return repository.check(title);}

    public void update(Manga manga){repository.update(manga);}

    public void delete(Manga manga) {
        repository.delete(manga);
    }
}
