package com.octopus.seriallist.data.serial;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.octopus.seriallist.EpisodesActivity;
import com.octopus.seriallist.R;

public class SerialListAdapter extends ListAdapter<Serial, SerialViewHolder> {


    public SerialListAdapter(@NonNull DiffUtil.ItemCallback<Serial> diffCallback) {
        super(diffCallback);

    }

    @Override
    public SerialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SerialViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.title_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SerialViewHolder holder, int position) {
        Serial current = getItem(position);
        holder.bind(current, current.getSeason());
        String title = "id_"+getItem(position).getTitle() +"_"+ getItem(position).getSeason()+"_id";
        int id = current.getId();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EpisodesActivity.class);
                intent.putExtra(EpisodesActivity.EXTRA_POS, title);
                intent.putExtra(EpisodesActivity.EXTRA_POS_ID, id);
                view.getContext().startActivity(intent);
            }
        });

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
