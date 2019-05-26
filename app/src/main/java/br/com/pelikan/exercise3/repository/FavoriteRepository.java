package br.com.pelikan.exercise3.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.pelikan.exercise3.dao.FavoriteDao;
import br.com.pelikan.exercise3.database.FavoriteRoomDatabase;
import br.com.pelikan.exercise3.entity.Favorite;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<Favorite>> allFavorites;

    public FavoriteRepository(Application application) {
        FavoriteRoomDatabase db = FavoriteRoomDatabase.getDatabase(application);
        favoriteDao = db.favoriteDao();
        allFavorites = favoriteDao.getAllFavorites();
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return allFavorites;
    }

    public void insert (Favorite favorite) {
        new insertAsyncTask(favoriteDao).execute(favorite);
    }

    public void deleteFavorite (Favorite favorite) {
        new deleteAsyncTask(favoriteDao).execute(favorite);
    }

    private static class insertAsyncTask extends AsyncTask
            <Favorite, Void, Void> {

        private FavoriteDao asyncTaskDao;
        insertAsyncTask(FavoriteDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Favorite... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask
            <Favorite, Void, Void> {

        private FavoriteDao asyncTaskDao;
        deleteAsyncTask(FavoriteDao dao) {
            asyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(final Favorite... params) {
            asyncTaskDao.delete(params[0]);
            return null;
        }
    }


}
