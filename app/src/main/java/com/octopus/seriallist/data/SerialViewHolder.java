package com.octopus.seriallist.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.R;

public class SerialViewHolder extends RecyclerView.ViewHolder {

    private final TextView serialItemView;


    public SerialViewHolder(View itemView) {
        super(itemView);
        serialItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        serialItemView.setText(text);
    }

    static SerialViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new SerialViewHolder(view);
    }
}
