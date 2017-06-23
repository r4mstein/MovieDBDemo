package ua.r4mstein.moviedbdemo.modules.main;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.films.popular_movies.PopularMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.genres.GenresFragment;
import ua.r4mstein.moviedbdemo.modules.lists.favorite_movies.FavoriteMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.lists.get_lists.GetListsFragment;
import ua.r4mstein.moviedbdemo.modules.lists.rated_movies.RatedMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.lists.watchlist.WatchlistFragment;

public class MainPresenter extends BaseActivityPresenter {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        addGenresFragment();
    }

    public void addGenresFragment() {
        getRouter().replaceFragment(new GenresFragment(), false);
    }

    public void addListsFragment() {
        getRouter().replaceFragment(new GetListsFragment(), false);
    }

    public void addFavoriteMoviesFragment() {
        getRouter().replaceFragment(new FavoriteMoviesFragment(), false);
    }

    public void addWatchlistFragment() {
        getRouter().replaceFragment(new WatchlistFragment(), false);
    }

    public void addRatedMoviesFragment() {
        getRouter().replaceFragment(new RatedMoviesFragment(), false);
    }

    public void addPopularMoviesFragment() {
        getRouter().replaceFragment(new PopularMoviesFragment(), false);
    }
}
