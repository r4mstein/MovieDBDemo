package ua.r4mstein.moviedbdemo.modules.genres;

import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;
import ua.r4mstein.moviedbdemo.data.providers.GenreProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class GenresPresenter extends BaseFragmentPresenter<GenresPresenter.GenresView> {

    private GenreProvider mGenreProvider = new GenreProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getGenres();
    }

    private void getGenres() {
        execute(mGenreProvider.getGenreMovieList(API_KEY),
                genreMovieModel -> getView().showResult(genreMovieModel),
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface GenresView extends FragmentView {

        void showResult(GenreMovieModel genreMovieModel);
    }
}
