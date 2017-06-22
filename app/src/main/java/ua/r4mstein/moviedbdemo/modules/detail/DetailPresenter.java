package ua.r4mstein.moviedbdemo.modules.detail;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.films.details.DetailsMovieFragment;

import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;

public class DetailPresenter extends BaseActivityPresenter {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        addMovieDetailsFragment(getArguments().getLong(DETAILS_MOVIE_FRAGMENT));
    }

    public void addMovieDetailsFragment(long movieId) {
        getRouter().replaceFragment(DetailsMovieFragment.newInstance(movieId), false);
    }
}
