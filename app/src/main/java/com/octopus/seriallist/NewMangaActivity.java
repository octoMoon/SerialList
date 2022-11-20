package com.octopus.seriallist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewMangaActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.octopus.seriallist.REPLY";
    private EditText mEditWordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manga);
        mEditWordView = findViewById(R.id.edit_word2);
        final Button button = findViewById(R.id.button_save2);

        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String title = mEditWordView.getText().toString();
               replyIntent.putExtra(EXTRA_REPLY, title);
               setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}