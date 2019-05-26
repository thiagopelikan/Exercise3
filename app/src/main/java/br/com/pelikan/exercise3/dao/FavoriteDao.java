package br.com.pelikan.exercise3.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.pelikan.exercise3.entity.Favorite;

@Dao
public interface FavoriteDao {

   @Insert
   void insert(Favorite favorite);

   @Update
   public void updateWords(Favorite... favorites);

   @Delete
   public void delete(Favorite favorite);

   @Query("DELETE FROM Favorite")
   void deleteAll();

   @Query("SELECT * from Favorite ORDER BY name ASC")
   LiveData<List<Favorite>> getAllFavorites();

}           
