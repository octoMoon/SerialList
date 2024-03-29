package com.octopus.seriallist.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.service.OnSwipeTouchListener;
import com.octopus.seriallist.R;
import com.octopus.seriallist.data.entity.Manga;
import com.octopus.seriallist.adapter.MangaListAdapter;
import com.octopus.seriallist.data.view.MangaViewModel;

import java.util.List;

public class MangaActivity extends AppCompatActivity {
    public static final int NEW_MANGA_ACTIVITY_REQUEST_CODE = 1;
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

        recyclerView.setOnTouchListener(new OnSwipeTouchListener(MangaActivity.this) {
            public void onSwipeRight() {
                Intent intent = new Intent(MangaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabmanga);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MangaActivity.this, NewMangaActivity.class);
            startActivityForResult(intent, NEW_MANGA_ACTIVITY_REQUEST_CODE);
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_MANGA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                Manga manga = new Manga(data.getStringExtra(NewSerialActivity.EXTRA_REPLY));
                mangaViewModel.insert(manga);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}