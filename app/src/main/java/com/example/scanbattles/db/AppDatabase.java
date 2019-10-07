package com.example.scanbattles.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.scanbattles.models.Monster;
import com.example.scanbattles.models.Team;
import com.example.scanbattles.models.User;

@Database(entities = {Monster.class, User.class, Team.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract MonsterDao monsterDao();
    public abstract UserDao userDao();
    public abstract TeamDao teamDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "appDB").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
