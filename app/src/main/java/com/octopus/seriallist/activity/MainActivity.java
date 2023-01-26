package com.octopus.seriallist.activity;


import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.octopus.seriallist.service.OnSwipeTouchListener;
import com.octopus.seriallist.R;
import com.octopus.seriallist.data.entity.Episode;
import com.octopus.seriallist.data.view.EpisodeViewModel;
import com.octopus.seriallist.data.entity.Serial;
import com.octopus.seriallist.adapter.SerialListAdapter;
import com.octopus.seriallist.data.view.SerialViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private SerialViewModel serialViewModel;
    private EpisodeViewModel episodeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SerialListAdapter adapter = new SerialListAdapter(new SerialListAdapter.SerialDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        serialViewModel = new ViewModelProvider(this).get(SerialViewModel.class);
        episodeViewModel = new ViewModelProvider(this).get(EpisodeViewModel.class);

        serialViewModel.getAllTitle().observe(this, words -> {
            adapter.submitList(words);

        });


        recyclerView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeLeft() {
                Intent intent = new Intent(MainActivity.this, MangaActivity.class);
                startActivity(intent);
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewSerialActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Serial serial = new Serial(data.getStringExtra(NewSerialActivity.EXTRA_REPLY), data.getIntExtra(NewSerialActivity.EXTRA_REPLY2, -1), data.getIntExtra(NewSerialActivity.EXTRA_REPLY3, -1));
            Integer id = serialViewModel.isEmpty(serial.getTitle(), serial.getSeason());
            if (id == 0) {
                Toast.makeText(
                        getApplicationContext(),
                        "Новое Анимэ"+id.toString(),
                        Toast.LENGTH_LONG).show();
            }
            if (id==1){
                Toast.makeText(
                        getApplicationContext(),
                        "Старое Анимэ"+id.toString(),
                        Toast.LENGTH_LONG).show();
            }

            if (id==(-4)){
                Toast.makeText(
                        getApplicationContext(),
                        "Ошибка"+id.toString(),
                        Toast.LENGTH_LONG).show();
            }
        serialViewModel.insert(serial);
        int i = 1;

        while (i <= serial.getEpisodes()) {
            episodeViewModel.insert(new Episode("id_" + serial.getTitle() + "_" + serial.getSeason() + "_id", i, false));
            i++;
        }
    } else{
        Toast.makeText(
                getApplicationContext(),
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show();
    }


}


}