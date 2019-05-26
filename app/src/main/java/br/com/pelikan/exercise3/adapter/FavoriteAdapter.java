package br.com.pelikan.exercise3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.pelikan.exercise3.R;
import br.com.pelikan.exercise3.converter.FavoriteModelConverter;
import br.com.pelikan.exercise3.entity.Favorite;
import br.com.pelikan.exercise3.ui.WebViewActivity;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private final Context context;
    private final List<Favorite> favoriteList;
    private final OnDeleteListener onDeleteListener;

    public FavoriteAdapter(Context context, List<Favorite> favoriteList, OnDeleteListener onDeleteListener) {
        this.context = context;
        this.favoriteList = favoriteList;
        this.onDeleteListener = onDeleteListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_favorite_item, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Favorite current = favoriteList.get(position);
        holder.bindView(current);
    }

    public void refresh(List<Favorite> favorites){
        favoriteList.clear();
        favoriteList.addAll(favorites);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        void bindView(final Favorite favorite) {
            ((TextView)itemView.findViewById(R.id.nameTextView)).setText(favorite.getName());
            itemView.findViewById(R.id.deleteImageButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteListener.onDeleteListener(favorite);
                }
            });
            if(itemView.getTag() == null) {
                itemView.setTag(favorite);
            }
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(WebViewActivity.TARGET_FAVORITE, FavoriteModelConverter.toFavoriteModel((Favorite)view.getTag()));
            context.startActivity(intent);
        }
    }

    public interface OnDeleteListener {

        void onDeleteListener(Favorite favorite);
    }
}
