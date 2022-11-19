package com.octopus.seriallist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewSerialActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.octopus.seriallist.REPLY";
    public static final String EXTRA_REPLY2 = "com.octopus.seriallist.REPLY2";
    public static final String EXTRA_REPLY3 = "com.octopus.seriallist.REPLY3";

    private EditText mEditWordView;
    private EditText mEditWordView2;
    private EditText mEditWordView3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_serial);
        mEditWordView = findViewById(R.id.edit_word);
        mEditWordView2 = findViewById(R.id.edit_word2);
        mEditWordView3 = findViewById(R.id.edit_word3);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditWordView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String title = mEditWordView.getText().toString();
                String temp = mEditWordView2.getText().toString();
                String temp2 = mEditWordView3.getText().toString();
                try {
                    int season = Integer.parseInt(temp);
                    int episodes = Integer.parseInt(temp2);
                    replyIntent.putExtra(EXTRA_REPLY, title);
                    replyIntent.putExtra(EXTRA_REPLY2, season);
                    replyIntent.putExtra(EXTRA_REPLY3, episodes);
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
                }
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
