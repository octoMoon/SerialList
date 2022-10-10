package com.octopus.seriallist.data.episode;

import android.app.Application;
import android.graphics.Paint;
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

        episodeItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                episode.setViewed(true);
                episodeViewModel.update(episode);
            }
        });
    }


    public void bind(Episode episode) {
        this.episode = episode;
        if (episode.isViewed()==true){
            episodeItemView.setPaintFlags(episodeItemView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        String s = "Episode ";
        episodeItemView.setText(s + episode.getNumber());
    }

    static EpisodeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episode_item, parent, false);
        return new EpisodeViewHolder(view);
    }
}
