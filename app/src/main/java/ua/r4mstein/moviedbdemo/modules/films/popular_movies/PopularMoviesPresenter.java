package ua.r4mstein.moviedbdemo.modules.films.popular_movies;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class PopularMoviesPresenter extends BaseFragmentPresenter<PopularMoviesPresenter.PopularMoviesView> {

    private MoviesProvider mMoviesProvider = new MoviesProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getPopularMovies(current_page);
    }

    public void getNextPage() {
        if (current_page < total_pages) getPopularMovies(current_page + 1);
    }

    public void getPopularMovies(long pageNumber) {
        execute(mMoviesProvider.getPopularMovies(API_KEY, pageNumber),
                popularMoviesModel -> {
                    current_page = pageNumber;
                    total_pages = popularMoviesModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(popularMoviesModel.getMovies());
                    else
                        getView().addList(popularMoviesModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface PopularMoviesView extends FragmentView {

        void setList(List<Movie> list);

        void addList(List<Movie> list);
    }
}
