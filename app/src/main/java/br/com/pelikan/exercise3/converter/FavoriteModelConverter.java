package br.com.pelikan.exercise3.converter;

import br.com.pelikan.exercise3.entity.Favorite;
import br.com.pelikan.exercise3.model.FavoriteModel;

public class FavoriteModelConverter {

    public static FavoriteModel toFavoriteModel(Favorite favoriteEntity){
        return new FavoriteModel(favoriteEntity.getUrl(), favoriteEntity.getName());

    }

}