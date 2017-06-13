package ua.r4mstein.moviedbdemo.modules.genres;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {

    private List<GenreMovieModel.Genre> mGenresList = new ArrayList<>();
    private GenresActionListener mGenresActionListener;

    public GenresAdapter(List<GenreMovieModel.Genre> genresList) {
        mGenresList = genresList;
    }

    @Override
    public GenresViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenresViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genres, parent, false));
    }

    @Override
    public void onBindViewHolder(GenresViewHolder holder, int position) {
        GenreMovieModel.Genre genre = mGenresList.get(position);

        holder.bindData(genre);
        holder.cvContainer.setOnClickListener(v -> mGenresActionListener.genresItemClicked(genre.getId()));
    }

    @Override
    public void onViewRecycled(GenresViewHolder holder) {
        super.onViewRecycled(holder);
        holder.unBindData();
    }

    @Override
    public int getItemCount() {
        return mGenresList.size();
    }

    public void setActionListener(GenresActionListener actionListener) {
        mGenresActionListener = actionListener;
    }

    public class GenresViewHolder extends RecyclerView.ViewHolder {

        private TextView tvGenres;
        private CardView cvContainer;

        private GenreMovieModel.Genre mGenreModel;

        public GenresViewHolder(View itemView) {
            super(itemView);

            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres_IG);
            cvContainer = (CardView) itemView.findViewById(R.id.cv_container_IG);
        }

        public void bindData(GenreMovieModel.Genre genreModel) {
            mGenreModel = genreModel;

            tvGenres.setText(mGenreModel.getName());
        }

        public void unBindData() {
            mGenreModel = null;
        }
    }
}
