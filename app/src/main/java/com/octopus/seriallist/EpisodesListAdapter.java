package com.octopus.seriallist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EpisodesListAdapter extends RecyclerView.Adapter<EpisodesListAdapter.EpisodesViewHolder> {

    ArrayList<Episode> episodes;


    public EpisodesListAdapter(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        holder.episodeView.setText(episodes.get(position).name);
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }


    public static class EpisodesViewHolder extends RecyclerView.ViewHolder {
        TextView episodeView;
        Episode episode;
        boolean silentUpdate;


        public EpisodesViewHolder(View view) {
            super(view);
            episodeView = (TextView) view.findViewById(R.id.textView_episode);
            episodeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    episodeView.setPaintFlags(episodeView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                }
            });
        }
    }
}
