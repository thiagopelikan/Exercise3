package br.com.pelikan.exercise3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.pelikan.exercise3.dao.FavoriteDao;
import br.com.pelikan.exercise3.entity.Favorite;

@Database(entities = {Favorite.class}, version = 1)
public abstract class FavoriteRoomDatabase extends RoomDatabase {
    public abstract FavoriteDao favoriteDao();

    private static FavoriteRoomDatabase INSTANCE;

    public static FavoriteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavoriteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FavoriteRoomDatabase.class, "favorite_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }
}
