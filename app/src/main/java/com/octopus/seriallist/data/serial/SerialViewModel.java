package com.octopus.seriallist.data.serial;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SerialViewModel extends AndroidViewModel {
    private SerialRepository repository;
    private LiveData<List<Serial>> allTitle;

    public SerialViewModel(Application application) {
        super(application);
        repository = new SerialRepository(application);
        allTitle = repository.getAllTitle();
    }

    public LiveData<List<Serial>> getAllTitle() {
        return allTitle;
    }

    public void updatePoster(int id, String poster) {repository.updatePoster(id, poster);}

    public int isEmpty(String title, int season){return repository.isEmpty(title,season);}

    public String selectPoster(int id) {return repository.selectPoster(id);}

    public void insert(Serial serial) {
        repository.insert(serial);
    }

    public void update(Serial serial){repository.update(serial);}

    public void delete(Serial serial) {
        repository.delete(serial);
    }
}
