package com.octopus.seriallist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.octopus.seriallist.data.episode.Episode;
import com.octopus.seriallist.data.episode.EpisodeViewModel;
import com.octopus.seriallist.data.episode.EpisodesListAdapter;
import com.octopus.seriallist.data.serial.SerialListAdapter;
import com.octopus.seriallist.data.serial.SerialViewModel;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {
    public static final String EXTRA_POS = "title";
    private EpisodeViewModel episodeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        String title = (String) getIntent().getExtras().get(EXTRA_POS);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_episodes);
        final EpisodesListAdapter adapter = new EpisodesListAdapter(new EpisodesListAdapter.EpisodeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);

        episodeViewModel.getAllById(title).observe(this, episodes -> {
            adapter.submitList(episodes);
        });

    }
}

