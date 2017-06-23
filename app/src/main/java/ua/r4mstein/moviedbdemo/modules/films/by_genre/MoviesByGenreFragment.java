package ua.r4mstein.moviedbdemo.modules.films.by_genre;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStates;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStatesAlternative;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.MathManager;

public class MoviesByGenreFragment extends BaseFragment<MoviesByGenrePresenter>
        implements MoviesByGenrePresenter.MoviesByGenreView {

    public static final String GENRE_ID = "genre_id";

    private RecyclerView mRecyclerView;
    private MoviesByGenreAdapter adapter;

    @Override
    protected int getTitle() {
        return R.string.title_movies_by_genre;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_movies_by_genre;
    }

    @Override
    protected MoviesByGenrePresenter initPresenter() {
        return new MoviesByGenrePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FMBG);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesByGenreAdapter(getViewContext());
        adapter.setMoviesClickListener(getMoviesClickListener());
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage();
                    return true;
                }));
    }

    @NonNull
    private MoviesClickListener getMoviesClickListener() {
        return new MoviesClickListener() {
            @Override
            public void moviesItemClicked(long movieId) {
                getPresenter().goToDetailScreen(movieId);
            }

            @Override
            public void moviesItemLongClicked(long movieId, int position) {
                getPresenter().getMovieAccountState(movieId);
            }

            @Override
            public void ratingViewClicked(long movieId, float oldRating) {
                FragmentManager manager = getFragmentManager();

                DialogRating dialogRating = new DialogRating();
                dialogRating.setDialogRatingClickListener(rating -> {
                    float sendRating = MathManager.getRating(rating);
                    Logger.d("positiveClicked: rating = " + sendRating);

                    getPresenter().rateMovie(movieId, dialogRating, sendRating);
                });
                dialogRating.show(manager, "DialogRating");
            }
        };
    }

    @NonNull
    private ChooseActionClickListener getChooseActionClickListener(final long movieId, final ChooseActionDialog dialog) {
        return new ChooseActionClickListener() {
            @Override
            public void favoriteClicked() {
                Logger.d("favoriteClicked");
                getPresenter().markAsFavorite(movieId, dialog, true);
            }

            @Override
            public void watchlistClicked() {
                Logger.d("watchlistClicked");
                getPresenter().addToWatchlist(movieId, dialog, true);
            }

            @Override
            public void removeFromFavoriteClicked() {
                Logger.d("removeFromFavoriteClicked");
                getPresenter().markAsFavorite(movieId, dialog, false);
            }

            @Override
            public void removeFromWatchlistClicked() {
                Logger.d("removeFromWatchlistClicked");
                getPresenter().addToWatchlist(movieId, dialog, false);
            }
        };
    }

    public static MoviesByGenreFragment newInstance(long genreId) {
        MoviesByGenreFragment fragment = new MoviesByGenreFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(GENRE_ID, genreId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void setList(List<Movie> list) {
        adapter.setData(list);
    }

    @Override
    public void addList(List<Movie> list) {
        adapter.addData(list);
    }

    @Override
    public void createDialog(long movieId, MovieAccountStates movieAccountStates) {
        FragmentManager manager = getFragmentManager();

        int addFavorite = View.GONE;
        int removeFavorite = View.VISIBLE;
        if (!movieAccountStates.getFavorite()) {
            addFavorite = View.VISIBLE;
            removeFavorite = View.GONE;
        }

        int addWatchlist = View.GONE;
        int removeWatchlist = View.VISIBLE;
        if (!movieAccountStates.getWatchlist()) {
            addWatchlist = View.VISIBLE;
            removeWatchlist = View.GONE;
        }

        ChooseActionDialog dialog = ChooseActionDialog.newInstance(addFavorite, addWatchlist, removeFavorite, removeWatchlist);
        dialog.setChooseActionClickListener(getChooseActionClickListener(movieId, dialog));
        dialog.show(manager, "ChooseActionDialog");
    }

    @Override
    public void createDialog(long movieId, MovieAccountStatesAlternative movieAccountStates) {
        FragmentManager manager = getFragmentManager();

        int addFavorite = View.GONE;
        int removeFavorite = View.VISIBLE;
        if (!movieAccountStates.getFavorite()) {
            addFavorite = View.VISIBLE;
            removeFavorite = View.GONE;
        }

        int addWatchlist = View.GONE;
        int removeWatchlist = View.VISIBLE;
        if (!movieAccountStates.getWatchlist()) {
            addWatchlist = View.VISIBLE;
            removeWatchlist = View.GONE;
        }

        ChooseActionDialog dialog = ChooseActionDialog.newInstance(addFavorite, addWatchlist, removeFavorite, removeWatchlist);
        dialog.setChooseActionClickListener(getChooseActionClickListener(movieId, dialog));
        dialog.show(manager, "ChooseActionDialog");
    }
}
