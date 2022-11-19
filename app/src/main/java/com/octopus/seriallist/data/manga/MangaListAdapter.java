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

    protected MangaListAdapter(@NonNull DiffUtil.ItemCallback<Manga> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MangaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MangaViewHolder holder, int position) {

    }

    public static class MangaDiff extends DiffUtil.ItemCallback<Manga>{


        @Override
        public boolean areItemsTheSame(@NonNull Manga oldItem, @NonNull Manga newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Manga oldItem, @NonNull Manga newItem) {
            return oldItem.getTitleName().equals(newItem.getTitleName());
        }
    }

    class MangaViewHolder extends RecyclerView.ViewHolder{
        private final TextView mangaItemView;
        private MangaViewModel mangaViewModel;
        Manga manga;

        public MangaViewHolder(@NonNull View itemView) {
            super(itemView);
            mangaItemView = itemView.findViewById(R.id.mangaTextView);
            mangaViewModel = new MangaViewModel(new Application());


        }

        public void bind(Manga manga){
            this.manga = manga;
            mangaItemView.setText(manga.getTitleName());
        }

        MangaViewHolder create(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.manga_item, parent, false);
            return new MangaViewHolder(view);
        }
    }
}
