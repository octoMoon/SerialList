package com.octopus.seriallist.data.manga;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.R;

public class MangaListAdapter extends ListAdapter<Manga, MangaListAdapter.MangaViewHolder> {

    public MangaListAdapter(@NonNull DiffUtil.ItemCallback<Manga> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MangaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.manga_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {
        Manga current = getItem(position);
        holder.bind(current);
    }

    public static class MangaDiff extends DiffUtil.ItemCallback<Manga> {


        @Override
        public boolean areItemsTheSame(@NonNull Manga oldItem, @NonNull Manga newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Manga oldItem, @NonNull Manga newItem) {
            return oldItem.getTitleName().equals(newItem.getTitleName());
        }
    }

    class MangaViewHolder extends RecyclerView.ViewHolder {
        View delete;
        View addEpisodes;
        private final TextView mangaItemView;
        private MangaViewModel mangaViewModel;
        Manga manga;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaItemView = itemView.findViewById(R.id.mangaTextView);
            delete = itemView.findViewById(R.id.deleteManga);
            addEpisodes = itemView.findViewById(R.id.addMangaEpisodes);
            mangaViewModel = new MangaViewModel(new Application());

            addEpisodes.setOnClickListener(new View.OnClickListener() {
               @Override
                public void onClick(View view) {
                  manga.setEpisodesNumber(manga.getEpisodesNumber() + 1);
                 mangaViewModel.update(manga);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mangaViewModel.delete(manga);
                }
            });
            
        }

        public void bind(Manga manga) {
            this.manga = manga;
            mangaItemView.setText(manga.getTitleName()+" "+manga.getEpisodesNumber()+" Episode");
        }

        MangaViewHolder create(ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.manga_item, parent, false);
            return new MangaViewHolder(view);
        }
    }
}
