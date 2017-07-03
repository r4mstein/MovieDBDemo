package ua.r4mstein.moviedbdemo.modules.main;

import android.content.Intent;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.films.popular_movies.PopularMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.films.search_movie.SearchMovieFragment;
import ua.r4mstein.moviedbdemo.modules.genres.GenresFragment;
import ua.r4mstein.moviedbdemo.modules.lists.favorite_movies.FavoriteMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.lists.get_lists.GetListsFragment;
import ua.r4mstein.moviedbdemo.modules.lists.rated_movies.RatedMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.lists.watchlist.WatchlistFragment;
import ua.r4mstein.moviedbdemo.modules.login.activity.LoginActivity;
import ua.r4mstein.moviedbdemo.modules.people.popular.PopularPeopleFragment;
import ua.r4mstein.moviedbdemo.modules.people.search_people.SearchPeopleFragment;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

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

    public void addPopularPeopleFragment() {
        getRouter().replaceFragment(new PopularPeopleFragment(), false);
    }

    public void addSearchPeopleFragment() {
        getRouter().replaceFragment(new SearchPeopleFragment(), false);
    }

    public void addSearchMoviesFragment() {
        getRouter().replaceFragment(new SearchMovieFragment(), false);
    }

    public void goToLoginScreen() {
        SharedPrefManager.getInstance().clear();

        int flags = Intent.FLAG_ACTIVITY_CLEAR_TOP;
        getRouter().startActivity(LoginActivity.class, flags, null);
        getRouter().finishActivity();
    }
}
