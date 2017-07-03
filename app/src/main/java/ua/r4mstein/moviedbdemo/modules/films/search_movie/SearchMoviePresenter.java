package ua.r4mstein.moviedbdemo.modules.films.search_movie;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.SearchProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SearchMoviePresenter extends BaseFragmentPresenter<SearchMoviePresenter.SearchMovieView> {

    private SearchProvider mSearchProvider = new SearchProvider();

    private long current_page;
    private long total_pages;

    public void getNextPage(String searchRequest) {
        if (current_page < total_pages) getSearchMovies(current_page + 1, searchRequest);
    }

    public void getSearchMovies(long pageNumber, String searchRequest) {
        execute(mSearchProvider.searchMovies(API_KEY, searchRequest, pageNumber),
                searchMoviesModel -> {
                    current_page = pageNumber;
                    total_pages = searchMoviesModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(searchMoviesModel.getMovies());
                    else
                        getView().addList(searchMoviesModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface SearchMovieView extends FragmentView {
        void setList(List<Movie> list);
        void addList(List<Movie> list);
    }
}
