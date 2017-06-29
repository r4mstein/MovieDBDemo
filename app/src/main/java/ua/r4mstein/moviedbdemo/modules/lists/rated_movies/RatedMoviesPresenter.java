package ua.r4mstein.moviedbdemo.modules.lists.rated_movies;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class RatedMoviesPresenter extends BaseFragmentPresenter<RatedMoviesPresenter.RatedMoviesView> {

    private AccountProvider mAccountProvider = new AccountProvider();
    private MoviesProvider mMoviesProvider = new MoviesProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getRatedMovies(current_page);
    }

    public void getNextPage() {
        if (current_page < total_pages) getRatedMovies(current_page + 1);
    }

    public void getRatedMovies(long pageNumber) {
        execute(mAccountProvider.getRatedMovies(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), pageNumber),
                favoriteMoviesModel -> {
                    current_page = pageNumber;
                    total_pages = favoriteMoviesModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(favoriteMoviesModel.getMovies());
                    else
                        getView().addList(favoriteMoviesModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void deleteRatingOfMovie(long movieId) {
        execute(mMoviesProvider.deleteRating(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> {
                            current_page = 1;
                            getRatedMovies(current_page);
                        }, null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface RatedMoviesView extends FragmentView {
        void setList(List<Movie> list);

        void addList(List<Movie> list);
    }
}
