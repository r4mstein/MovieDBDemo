package ua.r4mstein.moviedbdemo.modules.detail;

import ua.r4mstein.moviedbdemo.modules.base.BaseActivityPresenter;
import ua.r4mstein.moviedbdemo.modules.films.details.DetailsMovieFragment;
import ua.r4mstein.moviedbdemo.modules.people.details.DetailsPeopleFragment;

import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;
import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_PERSON_FRAGMENT;

public class DetailPresenter extends BaseActivityPresenter {

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        Long movie = getArguments().getLong(DETAILS_MOVIE_FRAGMENT, 0);
        Long person = getArguments().getLong(DETAILS_PERSON_FRAGMENT, 0);
        if (movie != 0) {
            addMovieDetailsFragment(movie);
        } else if (person != 0){
            addDetailsPeopleFragment(person);
        }
    }

    private void addMovieDetailsFragment(long movieId) {
        getRouter().replaceFragment(DetailsMovieFragment.newInstance(movieId), false);
    }

    private void addDetailsPeopleFragment(long personId) {
        getRouter().replaceFragment(DetailsPeopleFragment.newInstance(personId), false);
    }
}
