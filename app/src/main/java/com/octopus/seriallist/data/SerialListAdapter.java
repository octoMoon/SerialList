package com.octopus.seriallist.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class SerialListAdapter extends ListAdapter<Serial, SerialViewHolder> {


    public SerialListAdapter(@NonNull DiffUtil.ItemCallback<Serial> diffCallback) {
        super(diffCallback);
    }

    @Override
    public SerialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SerialViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(SerialViewHolder holder, int position) {
        Serial current = getItem(position);
        holder.bind(current, current.getSeason());
   }

    public static class SerialDiff extends DiffUtil.ItemCallback<Serial> {

        @Override
        public boolean areItemsTheSame(@NonNull Serial oldItem, @NonNull Serial newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Serial oldItem, @NonNull Serial newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
