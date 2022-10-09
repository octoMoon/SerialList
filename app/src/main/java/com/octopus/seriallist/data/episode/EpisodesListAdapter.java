package com.octopus.seriallist.data.episode;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.EpisodesActivity;
import com.octopus.seriallist.R;
import com.octopus.seriallist.data.serial.Serial;
import com.octopus.seriallist.data.serial.SerialViewHolder;

import java.util.ArrayList;

public class EpisodesListAdapter extends ListAdapter<Episode, EpisodeViewHolder> {


    public EpisodesListAdapter(@NonNull DiffUtil.ItemCallback<Episode> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode current = getItem(position);
        holder.bind(current);
    }

    public static class EpisodeDiff extends DiffUtil.ItemCallback<Episode> {

        @Override
        public boolean areItemsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem.getNumber() == newItem.getNumber();
        }
    }
}
