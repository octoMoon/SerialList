package com.octopus.seriallist.data.serial;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.octopus.seriallist.EpisodesActivity;
import com.octopus.seriallist.R;

public class SerialViewHolder extends RecyclerView.ViewHolder {

    private final TextView serialItemView;
    View delete;
    SerialViewModel serialViewModel;
    Serial serial;

    public SerialViewHolder(View itemView) {
        super(itemView);
        serialItemView = itemView.findViewById(R.id.textView);
        delete = itemView.findViewById(R.id.delete);
        serialViewModel = new SerialViewModel(new Application());

        delete.setOnClickListener(view -> serialViewModel.delete(serial));
        serialItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EpisodesActivity.class);
                String titleId = serialItemView.getText().toString().replace(" season ","");
                intent.putExtra("title",titleId );
                view.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Serial serial, int i) {
        this.serial = serial;
        String season = " season ";
        serialItemView.setText(serial.getTitle() +season+ serial.getSeason());
    }

    static SerialViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.title_item, parent, false);
        return new SerialViewHolder(view);
    }
}
