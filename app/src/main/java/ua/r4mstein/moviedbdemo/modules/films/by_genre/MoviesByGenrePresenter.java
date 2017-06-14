package ua.r4mstein.moviedbdemo.modules.films.by_genre;

import ua.r4mstein.moviedbdemo.data.models.response.MoviesByGenreModel;
import ua.r4mstein.moviedbdemo.data.providers.GenreProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class MoviesByGenrePresenter extends BaseFragmentPresenter<MoviesByGenrePresenter.MoviesByGenreView> {

    private GenreProvider mGenreProvider = new GenreProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getMovies();
    }

    public void getMovies() {
        execute(mGenreProvider.getMoviesByGenre(getArguments().getLong(MoviesByGenreFragment.GENRE_ID), API_KEY),
                moviesByGenreModel -> getView().showResult(moviesByGenreModel),
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface MoviesByGenreView extends FragmentView {

        void showResult(MoviesByGenreModel model);
    }
}
