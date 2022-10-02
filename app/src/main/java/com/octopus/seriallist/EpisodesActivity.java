package com.octopus.seriallist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.octopus.seriallist.data.episode.Episode;
import com.octopus.seriallist.data.episode.EpisodesListAdapter;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {
    ArrayList<Episode> episodes = new ArrayList<>();
    int intepisodes;
    RecyclerView.LayoutManager layoutManager;
    EpisodesListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);
        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_episodes);
        adapter = new EpisodesListAdapter(episodes);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            intepisodes = arguments.getInt("episodes");
        }


        for (int i = 1; i < intepisodes + 1; i++) {
            episodes.add(new Episode(i, "Episode " + i, false));
        }
    }
}

