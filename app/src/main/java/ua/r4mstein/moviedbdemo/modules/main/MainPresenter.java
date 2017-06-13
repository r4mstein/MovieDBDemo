package ua.r4mstein.moviedbdemo.modules.main;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.genres.GenresFragment;

public class MainPresenter extends BaseActivityPresenter {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        addGenresFragment();
    }

    public void addGenresFragment() {
        getRouter().replaceFragment(new GenresFragment(), false);
    }
}
