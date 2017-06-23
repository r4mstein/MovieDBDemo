package ua.r4mstein.moviedbdemo.modules.films.by_genre;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStates;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStatesAlternative;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.data.providers.GenreProvider;
import ua.r4mstein.moviedbdemo.data.providers.MoviesProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.detail.DetailActivity;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.DELETE_RATING;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.FAVORITE_WATCHLIST;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.SET_RATING;
import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;
import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;

public class MoviesByGenrePresenter extends BaseFragmentPresenter<MoviesByGenrePresenter.MoviesByGenreView> {

    private GenreProvider mGenreProvider = new GenreProvider();
    private AccountProvider mAccountProvider = new AccountProvider();
    private MoviesProvider mMoviesProvider = new MoviesProvider();

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

    public void markAsFavorite(long movieId, ChooseActionDialog dialog, boolean choice) {
        MarkFavoriteSendModel sendModel = new MarkFavoriteSendModel();
        sendModel.setFavorite(choice);
        sendModel.setMediaId(movieId);
        sendModel.setMediaType("movie");

        execute(mAccountProvider.markAsFavorite(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void addToWatchlist(long movieId, ChooseActionDialog dialog, boolean choice) {
        AddToWatchlistSendModel sendModel = new AddToWatchlistSendModel();
        sendModel.setMediaType("movie");
        sendModel.setMediaId(movieId);
        sendModel.setWatchlist(choice);

        execute(mAccountProvider.addToWatchList(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void rateMovie(long movieId, DialogRating dialog, float rating) {
        RateMovieSendModel sendModel = new RateMovieSendModel();
        sendModel.setValue(rating);

        execute(mMoviesProvider.rateMovie(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void goToDetailScreen(long movieId) {
        Bundle bundle = new Bundle();
        bundle.putLong(DETAILS_MOVIE_FRAGMENT, movieId);
        getRouter().startActivity(DetailActivity.class, 0, bundle);
    }

    public void getMovieAccountState(long movieId, String reasonType) {
        execute(mMoviesProvider.getMovieAccountStates(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                movieAccountStates -> {
                    switch (reasonType) {
                        case FAVORITE_WATCHLIST:
                            getView().createDialog(movieId, movieAccountStates);
                            break;
                        case SET_RATING:
                            getView().createRatingDialog(movieId, movieAccountStates);
                            break;
                        case DELETE_RATING:
                            showDeleteRatingDialog(movieId, true);
                            break;
                    }
                },
                throwable -> {
                    if (throwable.getClass().equals(com.google.gson.JsonSyntaxException.class)) {
                        Logger.d(throwable.getMessage());
                        getMovieAccountStateAlternative(movieId, reasonType);
                    }
                });
    }

    private void getMovieAccountStateAlternative(final long movieId, String reasonType) {
        execute(mMoviesProvider.getMovieAccountStatesAlternative(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                movieAccountStatesAlternative -> {
                    switch (reasonType) {
                        case FAVORITE_WATCHLIST:
                            getView().createDialog(movieId, movieAccountStatesAlternative);
                            break;
                        case SET_RATING:
                            getView().createRatingDialog(movieId, movieAccountStatesAlternative);
                            break;
                        case DELETE_RATING:
                            showDeleteRatingDialog(movieId, false);
                            break;
                    }
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void deleteRatingOfMovie(long movieId) {
        execute(mMoviesProvider.deleteRating(movieId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> {}, null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void showDeleteRatingDialog(long movieId, boolean isRated) {
        if (isRated) {
            getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_delete_rating_message),
                    v -> {
                        Logger.d("positive clicked");
                        deleteRatingOfMovie(movieId);
                    },
                    v -> Logger.d("negative clicked"));
        } else {
            getRouter().showDialog(new InfoDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_delete_rating_attention_message),
                    v -> {}, null);
        }
    }

    interface MoviesByGenreView extends FragmentView {

        void setList(List<Movie> list);
        void addList(List<Movie> list);
        void createDialog(long movieId, MovieAccountStates movieAccountStates);
        void createDialog(long movieId, MovieAccountStatesAlternative movieAccountStates);
        void createRatingDialog(long movieId, MovieAccountStates movieAccountStates);
        void createRatingDialog(long movieId, MovieAccountStatesAlternative movieAccountStates);
        Resources getAppResources();
    }
}
