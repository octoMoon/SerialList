package com.octopus.seriallist.data;

import android.app.Application;

import androidx.annotation.NonNull;
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

    public void insert(Serial serial) {
        repository.insert(serial);
    }

    public void delete(Serial serial) {
        repository.delete(serial);
    }
}
