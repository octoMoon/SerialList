package com.octopus.seriallist.adapter;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.R;
import com.octopus.seriallist.data.entity.Serial;
import com.octopus.seriallist.data.view.EpisodeViewModel;
import com.octopus.seriallist.data.view.SerialViewModel;

public class SerialViewHolder extends RecyclerView.ViewHolder {

    private final TextView serialItemView;
    View delete;
    SerialViewModel serialViewModel;
    EpisodeViewModel episodeViewModel;
    Serial serial;

    public SerialViewHolder(View itemView) {
        super(itemView);
        serialItemView = itemView.findViewById(R.id.textView);
        delete = itemView.findViewById(R.id.delete);
        serialViewModel = new SerialViewModel(new Application());
        episodeViewModel = new EpisodeViewModel(new Application());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialViewModel.delete(serial);
                episodeViewModel.deleteAll("id_"+serial.getTitle()+"_"+serial.getSeason()+"_id");
            }
        });

    }

    public void bind(Serial serial, int i) {
        this.serial = serial;
        String season = " season ";
        serialItemView.setText(serial.getTitle() + season + serial.getSeason());
    }

    static SerialViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_item, parent, false);
        return new SerialViewHolder(view);
    }
}
