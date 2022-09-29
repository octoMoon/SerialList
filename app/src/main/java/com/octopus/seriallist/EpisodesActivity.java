package com.octopus.seriallist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.octopus.seriallist.data.SerialListAdapter;
import com.octopus.seriallist.data.SerialViewModel;

import java.util.ArrayList;

public class EpisodesActivity extends AppCompatActivity {
    ArrayList<Episode> episodes = new ArrayList<>();
    Episode episode;
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
        Episode ep = new Episode(1,"Episode 1", false);
        episodes.add(ep);
        episodes.add(new Episode(2, "Episode 2", false));
        episodes.add(new Episode(3, "Episode 3", false));
        episodes.add(new Episode(4, "Episode 4", false));

    }
}

