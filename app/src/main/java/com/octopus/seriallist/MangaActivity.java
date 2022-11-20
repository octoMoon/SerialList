package com.octopus.seriallist;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.data.manga.Manga;
import com.octopus.seriallist.data.manga.MangaListAdapter;
import com.octopus.seriallist.data.manga.MangaViewModel;
import com.octopus.seriallist.databinding.ActivityMangaBinding;

public class MangaActivity extends AppCompatActivity {
private MangaViewModel mangaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);

        RecyclerView recyclerView = findViewById(R.id.recyclerviewmanga);
        final MangaListAdapter adapter = new MangaListAdapter(new MangaListAdapter.MangaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mangaViewModel = new ViewModelProvider(this).get(MangaViewModel.class);

        mangaViewModel.getAllEpisodes().observe(this, mangas -> {
            adapter.submitList(mangas);
        });

    }
}