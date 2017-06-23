package ua.r4mstein.moviedbdemo.modules.films.popular_movies;

import android.os.Bundle;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.models.response.PopularMoviesModel;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.detail.DetailActivity;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;
import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;

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
                new Consumer<PopularMoviesModel>() {
                    @Override
                    public void accept(@NonNull PopularMoviesModel popularMoviesModel) throws Exception {
                        current_page = pageNumber;
                        total_pages = popularMoviesModel.getTotalPages();

                        if (current_page == 1)
                            getView().setList(popularMoviesModel.getMovies());
                        else
                            getView().addList(popularMoviesModel.getMovies());
                    }
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void goToDetailScreen(long movieId) {
        Bundle bundle = new Bundle();
        bundle.putLong(DETAILS_MOVIE_FRAGMENT, movieId);
        getRouter().startActivity(DetailActivity.class, 0, bundle);
    }

    interface PopularMoviesView extends FragmentView {

        void setList(List<Movie> list);
        void addList(List<Movie> list);
    }
}
