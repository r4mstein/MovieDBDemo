package ua.r4mstein.moviedbdemo.modules.films.by_genre;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.GenreProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class MoviesByGenrePresenter extends BaseFragmentPresenter<MoviesByGenrePresenter.MoviesByGenreView> {

    private GenreProvider mGenreProvider = new GenreProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getMovies(current_page);
    }

    public void getNextPage() {
        if (current_page < total_pages) getMovies(current_page + 1);
    }

    public void getMovies(long pageNumber) {
        execute(mGenreProvider.getMoviesByGenre(getArguments().getLong(MoviesByGenreFragment.GENRE_ID), API_KEY, pageNumber),
                moviesByGenreModel -> {
                    current_page = pageNumber;
                    total_pages = moviesByGenreModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(moviesByGenreModel.getMovies());
                    else
                        getView().addList(moviesByGenreModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface MoviesByGenreView extends FragmentView {

        void setList(List<Movie> list);

        void addList(List<Movie> list);
    }
}
