package com.octopus.seriallist.adapter;

import android.app.Application;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.R;
import com.octopus.seriallist.data.entity.Episode;
import com.octopus.seriallist.data.view.EpisodeViewModel;

public class EpisodesListAdapter extends ListAdapter<Episode, EpisodesListAdapter.EpisodeViewHolder> {
    private final int TYPE_ITEM1 = 0;
    private final int TYPE_ITEM2 = 1;

    public EpisodesListAdapter(@NonNull DiffUtil.ItemCallback<Episode> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ITEM1:
                return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.episode_item, parent, false));
            default:
                return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.watched_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        Episode current = getItem(position);
        int type = getItemViewType(position);
        switch (type){
            case TYPE_ITEM2:holder.bind2(current);
            break;
            case TYPE_ITEM1:holder.bind(current);
        }


    }

    @Override
    public int getItemViewType(int position) {
        Episode current = getItem(position);
        if (current.isViewed()==false) return TYPE_ITEM1;
        return TYPE_ITEM2;
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

    class EpisodeViewHolder extends RecyclerView.ViewHolder {
        private final TextView episodeItemView;
        private final TextView watchedEpisodeItemView;
        EpisodeViewModel episodeViewModel;
        Episode episode;


        public EpisodeViewHolder(View itemView) {
            super(itemView);
            episodeItemView = itemView.findViewById(R.id.textView_episode);
            watchedEpisodeItemView = itemView.findViewById(R.id.textView_watched);
            episodeViewModel = new EpisodeViewModel(new Application());

        }

        public void bind2(Episode episode) {
            this.episode = episode;
            String s = "Episode ";
            watchedEpisodeItemView.setPaintFlags(watchedEpisodeItemView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            watchedEpisodeItemView.setText(s + episode.getNumber());

        }

        public void bind(Episode episode) {
            this.episode = episode;
            String s = "Episode ";
            episodeItemView.setText(s + episode.getNumber());

            episodeItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    episode.setViewed(true);
                    episodeViewModel.update(episode);
                }
            });
        }


    }
}
