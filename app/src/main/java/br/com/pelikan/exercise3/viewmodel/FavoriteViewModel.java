package br.com.pelikan.exercise3.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.pelikan.exercise3.entity.Favorite;
import br.com.pelikan.exercise3.repository.FavoriteRepository;

public class FavoriteViewModel extends AndroidViewModel {
    private FavoriteRepository repository;
    private LiveData<List<Favorite>> allFavorites;

    public FavoriteViewModel(Application application) {
        super(application);
        repository = new FavoriteRepository(application);
        allFavorites = repository.getAllFavorites();
    }

    public LiveData<List<Favorite>> getAllFavorites() {
        return allFavorites;
    }
    public void insert(Favorite favorite) {
        repository.insert(favorite);
    }
    public void deleteFavorite(Favorite favorite) {
        repository.deleteFavorite(favorite);
    }

}
