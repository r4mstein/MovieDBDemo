package ua.r4mstein.moviedbdemo.modules.genres;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.r4mstein.moviedbdemo.R;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {

    private List<String> mGenresList = new ArrayList<>();

    public GenresAdapter(List<String> genresList) {
        mGenresList = genresList;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenresViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genres, parent, false));
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, int position) {
        holder.tvGenres.setText(mGenresList.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenresList.size();
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {

        private TextView tvGenres;

        public GenresViewHolder(View itemView) {
            super(itemView);

            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres_IG);
        }
    }
}
