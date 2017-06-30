package ua.r4mstein.moviedbdemo.modules.films.details;

import java.util.ArrayList;
import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class DetailsMoviePresenter extends BaseFragmentPresenter<DetailsMoviePresenter.DetailsMovieView> {

    private MoviesProvider mMoviesProvider = new MoviesProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        long id = getArguments().getLong(DetailsMovieFragment.MOVIE_ID);
        getMovieDetails(id);
    }

    public void getMovieDetails(long movieId) {
        execute(mMoviesProvider.getMovieDetails(movieId, API_KEY),
                movieDetailsModel -> {
                    List<MovieDetailsModel> res = new ArrayList<>();
                    res.add(movieDetailsModel);
                    getView().addList(res);
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface DetailsMovieView extends FragmentView {

        void addList(List<MovieDetailsModel> list);
    }
}
