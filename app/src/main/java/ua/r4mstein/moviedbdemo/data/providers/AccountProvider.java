package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.AccountApi;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.FavoriteMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.data.models.response.RatedMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.UserModel;
import ua.r4mstein.moviedbdemo.data.models.response.WatchlistModel;

public class AccountProvider extends BaseProvider implements AccountApi {

    @Override
    public Observable<GetListsModel> getCreatedLists(String apiKey, String sessionId, long page) {
        return api.getCreatedLists(apiKey, sessionId, page);
    }

    @Override
    public Observable<UserModel> getAccountDetails(String apiKey, String sessionId) {
        return api.getAccountDetails(apiKey, sessionId);
    }

    @Override
    public Observable<AddMovieToListModel> markAsFavorite(long accountId, String apiKey, String sessionId,
                                                          MarkFavoriteSendModel sendModel) {
        return api.markAsFavorite(accountId, apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<FavoriteMoviesModel> getFavoriteMovies(long accountId, String apiKey, String sessionId, long page) {
        return api.getFavoriteMovies(accountId, apiKey, sessionId, page);
    }

    @Override
    public Observable<AddMovieToListModel> addToWatchList(long accountId, String apiKey, String sessionId, AddToWatchlistSendModel sendModel) {
        return api.addToWatchList(accountId, apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<WatchlistModel> getWatchlist(long accountId, String apiKey, String sessionId, long page) {
        return api.getWatchlist(accountId, apiKey, sessionId, page);
    }

    @Override
    public Observable<RatedMoviesModel> getRatedMovies(long accountId, String apiKey, String sessionId, long page) {
        return api.getRatedMovies(accountId, apiKey, sessionId, page);
    }
}
