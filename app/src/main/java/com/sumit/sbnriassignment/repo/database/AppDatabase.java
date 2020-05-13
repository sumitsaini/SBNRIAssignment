package com.sumit.sbnriassignment.repo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sumit.sbnriassignment.model.RepoDb;
import com.sumit.sbnriassignment.repo.database.dao.RepoDao;


@Database(entities = {RepoDb.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "SBNRI.db").build();

                    System.out.println("db created");
                }
            }
        }
        return INSTANCE;
    }

    public abstract RepoDao getRepoDao();

}
