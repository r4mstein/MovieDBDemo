package ua.r4mstein.moviedbdemo.modules.lists.rated_movies;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreAdapter;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesClickListener;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.MathManager;

public class RatedMoviesFragment extends BaseFragment<RatedMoviesPresenter>
        implements RatedMoviesPresenter.RatedMoviesView {

    private RecyclerView mRecyclerView;
    private MoviesByGenreAdapter adapter;

    @Override
    protected int getTitle() {
        return R.string.title_rated_movies;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_rated_movies;
    }

    @Override
    protected RatedMoviesPresenter initPresenter() {
        return new RatedMoviesPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FRM);
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
            FragmentManager manager = getFragmentManager();

            @Override
            public void moviesItemClicked(long movieId) {
                getPresenter().goToDetailScreen(movieId);
            }

            @Override
            public void moviesItemLongClicked(long movieId, int position) {
                ChooseActionDialog dialog = ChooseActionDialog.newInstance(View.VISIBLE, View.VISIBLE, View.GONE, View.GONE);
                dialog.setChooseActionClickListener(getChooseActionClickListener(movieId, dialog));
                dialog.show(manager, "ChooseActionDialog");
            }

            @Override
            public void ratingViewClicked(long movieId, float oldRating) {
                DialogRating dialogRating = DialogRating.newInstance(oldRating);
                dialogRating.setDialogRatingClickListener(rating -> {
                    float sendRating = MathManager.getRating(rating);
                    Logger.d("positiveClicked: rating = " + sendRating);

                    getPresenter().rateMovie(movieId, dialogRating, sendRating);
                });
                dialogRating.show(manager, "DialogRating");
            }

            @Override
            public void ratingViewLongClicked(long movieId) {
                getPresenter().showDeleteRatingDialog(movieId);
            }
        };
    }

    @NonNull
    private ChooseActionClickListener getChooseActionClickListener(long movieId, ChooseActionDialog dialog) {
        return new ChooseActionClickListener() {
            @Override
            public void favoriteClicked() {
                Logger.d("favoriteClicked");
                getPresenter().markAsFavorite(movieId, dialog);
            }

            @Override
            public void watchlistClicked() {
                Logger.d("watchlistClicked");
                getPresenter().addToWatchlist(movieId, dialog);
            }

            @Override
            public void removeFromFavoriteClicked() {
                Logger.d("removeFromFavoriteClicked");
            }

            @Override
            public void removeFromWatchlistClicked() {
                Logger.d("removeFromWatchlistClicked");
            }
        };
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
    public Resources getAppResources() {
        return getResources();
    }
}
