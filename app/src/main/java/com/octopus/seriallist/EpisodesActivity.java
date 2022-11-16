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
    private ImageView imageView;
    private String image;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);



        String title = (String) getIntent().getExtras().get(EXTRA_POS);
        id = getIntent().getIntExtra(EXTRA_POS_ID, 0);

       // list = episodeViewModel.getAllById("id_naruto_1_id").observe(this, episodes -> ep );

        RecyclerView recyclerView = findViewById(R.id.recyclerview_episodes);
        final EpisodesListAdapter adapter = new EpisodesListAdapter(new EpisodesListAdapter.EpisodeDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);
        serialViewModel = new ViewModelProvider(this).get(SerialViewModel.class);


       // adapter.submitList(list);
        episodeViewModel.getAllById(title).observe(this, episodes -> {
            adapter.submitList(episodes);
        });

        imageView = findViewById(R.id.imageView);
        imageView.setClickable(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
                gallery.setType("image/*");
                startActivityForResult(gallery, 1);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}

