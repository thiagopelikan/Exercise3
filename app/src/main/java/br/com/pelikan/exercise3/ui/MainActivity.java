package br.com.pelikan.exercise3.ui;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.pelikan.exercise3.R;
import br.com.pelikan.exercise3.adapter.FavoriteAdapter;
import br.com.pelikan.exercise3.entity.Favorite;
import br.com.pelikan.exercise3.viewmodel.FavoriteViewModel;

public class MainActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    private FavoriteViewModel favoriteViewModel;
    private FavoriteAdapter favoriteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setTitle("App de Favoritos");


        recyclerView = findViewById(R.id.recyclerView);
        favoriteAdapter = new FavoriteAdapter(this, new ArrayList<Favorite>(), new FavoriteAdapter.OnDeleteListener() {
            @Override
            public void onDeleteListener(Favorite favorite) {
                favoriteViewModel.deleteFavorite(favorite);
            }
        });

        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final Observer<List<Favorite>> favoritesObserver =
                new Observer<List<Favorite>>() {
                    @Override
                    public void onChanged(@Nullable final List<Favorite> favoriteList) {
                        Log.d("TAG", "favorites SIZE " + favoriteList.size());
                        favoriteAdapter.refresh(favoriteList);
                    }
                };

        favoriteViewModel = ViewModelProviders.of(this).get(FavoriteViewModel.class);
        favoriteViewModel.getAllFavorites().observe(this, favoritesObserver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.add_menu) {
            LayoutInflater factory = LayoutInflater.from(this);

            final View addFavoriteView = factory.inflate(R.layout.layout_add_favorite, null);

            final EditText nameEditText = (EditText) addFavoriteView.findViewById(R.id.nameEditText);
            final EditText urlEditText = (EditText) addFavoriteView.findViewById(R.id.urlEditText);

            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Adicionar Favorito:").setView(addFavoriteView).setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            Favorite favorite = new Favorite(urlEditText.getText().toString(), nameEditText.getText().toString());
                            favoriteViewModel.insert(favorite);
                        }
                    }).setNegativeButton("Cancel",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            dialog.dismiss();
                        }
                    });
            alert.show();
            return true;
        }
        return false;

    }
}
