package com.octopus.seriallist.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.octopus.seriallist.data.entity.Episode;
import com.octopus.seriallist.data.dao.EpisodeDao;
import com.octopus.seriallist.data.entity.Manga;
import com.octopus.seriallist.data.dao.MangaDao;
import com.octopus.seriallist.data.entity.Serial;
import com.octopus.seriallist.data.dao.SerialDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Serial.class, Episode.class, Manga.class}, version = 1, exportSchema = false)
public abstract class SerialRoomDatabase extends RoomDatabase {

    public abstract SerialDao serialDao();
    public abstract MangaDao mangaDao();
    public abstract EpisodeDao episodeDao();

    private static volatile SerialRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SerialRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SerialRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    SerialRoomDatabase.class, "beta_1.4")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                SerialDao sDao = INSTANCE.serialDao();
                EpisodeDao eDao = INSTANCE.episodeDao();
                MangaDao mDao = INSTANCE.mangaDao();
            });
        }
    };

}
