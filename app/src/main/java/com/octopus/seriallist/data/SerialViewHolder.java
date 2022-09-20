package com.octopus.seriallist.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.MainActivity;
import com.octopus.seriallist.NewSerialActivity;
import com.octopus.seriallist.R;

public class SerialViewHolder extends RecyclerView.ViewHolder {

    private final TextView serialItemView;
    private final View delete;
    SerialViewModel serialViewModel;
    Serial serial;


    public SerialViewHolder(View itemView) {
        super(itemView);
        serialItemView = itemView.findViewById(R.id.textView);
        delete = itemView.findViewById(R.id.delete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                serialViewModel.delete(serial);
            }
        });
    }

    public void bind(Serial serial, int i) {
        this.serial = serial;
        String s = " season ";
        serialItemView.setText(serial.getTitle() + s + i);


    }

    static SerialViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_item, parent, false);
        return new SerialViewHolder(view);
    }
}
