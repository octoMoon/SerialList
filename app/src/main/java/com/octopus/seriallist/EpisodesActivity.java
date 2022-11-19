package com.octopus.seriallist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.octopus.seriallist.data.episode.Episode;
import com.octopus.seriallist.data.episode.EpisodeViewModel;
import com.octopus.seriallist.data.episode.EpisodesListAdapter;
import com.octopus.seriallist.data.serial.SerialViewModel;

import java.util.List;

public class EpisodesActivity extends AppCompatActivity {
    LiveData<List<Episode>> list;
    public static final String EXTRA_POS = "title";
    public static final String EXTRA_POS_ID = "id";
    private EpisodeViewModel episodeViewModel;
    private SerialViewModel serialViewModel;
    private TextView textView;
    private String episodeTitle;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        textView = findViewById(R.id.episodeTitle);
        String title = (String) getIntent().getExtras().get(EXTRA_POS);
        id = getIntent().getIntExtra(EXTRA_POS_ID, 0);

        episodeTitle = title.replace("id_","").replace("_id", "").replace("_"," season " ).toUpperCase();

        textView.setText(episodeTitle);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_episodes);
        final EpisodesListAdapter adapter = new EpisodesListAdapter(new EpisodesListAdapter.EpisodeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        serialViewModel = new ViewModelProvider(this).get(SerialViewModel.class);

        episodeViewModel.getAllById(title).observe(this, episodes -> {
            adapter.submitList(episodes);
        });

   }
}

