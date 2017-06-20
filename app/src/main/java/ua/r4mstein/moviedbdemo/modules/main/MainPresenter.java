package ua.r4mstein.moviedbdemo.modules.main;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.genres.GenresFragment;
import ua.r4mstein.moviedbdemo.modules.lists.favorite_movies.FavoriteMoviesFragment;
import ua.r4mstein.moviedbdemo.modules.lists.get_lists.GetListsFragment;

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
}
