package ua.r4mstein.moviedbdemo.modules.lists.watchlist;

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

public class WatchlistFragment extends BaseFragment<WatchlistPresenter>
        implements WatchlistPresenter.WatchlistMoviesView{

    private RecyclerView mRecyclerView;
    private MoviesByGenreAdapter adapter;

    @Override
    protected int getTitle() {
        return R.string.title_watchlist;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_watchlist;
    }

    @Override
    protected WatchlistPresenter initPresenter() {
        return new WatchlistPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FW);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesByGenreAdapter(getViewContext());
        adapter.setMoviesClickListener(new MoviesClickListener() {
            FragmentManager manager = getFragmentManager();

            @Override
            public void moviesItemClicked(long movieId) {

            }

            @Override
            public void moviesItemLongClicked(long movieId, int position) {
                ChooseActionDialog dialog = ChooseActionDialog.newInstance(View.VISIBLE, View.GONE, View.GONE, View.VISIBLE);
                dialog.setChooseActionClickListener(getChooseActionClickListener(movieId, dialog));
                dialog.show(manager, "ChooseActionDialog");
            }

            @Override
            public void ratingViewClicked(long movieId) {
                DialogRating dialogRating = new DialogRating();
                dialogRating.setDialogRatingClickListener(rating -> {
                    float sendRating = MathManager.getRating(rating);
                    Logger.d("positiveClicked: rating = " + sendRating);

                    getPresenter().rateMovie(movieId, dialogRating, sendRating);
                });
                dialogRating.show(manager, "DialogRating");
            }
        });
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage();
                    return true;
                }));
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
            }

            @Override
            public void removeFromFavoriteClicked() {
                Logger.d("removeFromFavoriteClicked");
            }

            @Override
            public void removeFromWatchlistClicked() {
                Logger.d("removeFromWatchlistClicked");
                getPresenter().removeFromWatchlist(movieId, dialog);
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
}
