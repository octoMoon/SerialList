package com.octopus.seriallist.data.episode;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.R;

public class EpisodeViewHolder extends RecyclerView.ViewHolder {
    private final TextView episodeItemView;
    EpisodeViewModel episodeViewModel;
    Episode episode;


    public EpisodeViewHolder(View itemView) {
        super(itemView);
        episodeItemView = itemView.findViewById(R.id.textView_episode);
        episodeViewModel = new EpisodeViewModel(new Application());
    }

    public void bind(Episode episode) {
        this.episode = episode;
        String s = "Episode ";
        episodeItemView.setText(s + episode.getNumber());
    }

    static EpisodeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_item, parent, false);
        return new EpisodeViewHolder(view);
    }
}
