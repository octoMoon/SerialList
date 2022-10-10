package com.octopus.seriallist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private ImageView imageView;
    public static final int SET_PHOTO = 1;


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
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            imageView.setImageURI(uri);
        }
    }
}

