package ua.r4mstein.moviedbdemo.modules.lists.watchlist;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class WatchlistPresenter extends BaseFragmentPresenter<WatchlistPresenter.WatchlistMoviesView> {

    private AccountProvider mAccountProvider = new AccountProvider();
    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getWatchlist(current_page);
    }

    public void getNextPage() {
        if (current_page < total_pages) getWatchlist(current_page + 1);
    }

    public void getWatchlist(long pageNumber) {
        execute(mAccountProvider.getWatchlist(SharedPrefManager.getInstance().getUser().getId(),
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

    public void removeFromWatchlist(long movieId, ChooseActionDialog dialog) {
        AddToWatchlistSendModel sendModel = new AddToWatchlistSendModel();
        sendModel.setMediaType("movie");
        sendModel.setMediaId(movieId);
        sendModel.setWatchlist(false);

        execute(mAccountProvider.addToWatchList(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> {
                            dialog.dismiss();
                            current_page = 1;
                            getWatchlist(current_page);
                        }, null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void markAsFavorite(long movieId, ChooseActionDialog dialog) {
        MarkFavoriteSendModel sendModel = new MarkFavoriteSendModel();
        sendModel.setFavorite(true);
        sendModel.setMediaId(movieId);
        sendModel.setMediaType("movie");

        execute(mAccountProvider.markAsFavorite(SharedPrefManager.getInstance().getUser().getId(),
                API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> dialog.dismiss(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface WatchlistMoviesView extends FragmentView {
        void setList(List<Movie> list);

        void addList(List<Movie> list);
    }
}
