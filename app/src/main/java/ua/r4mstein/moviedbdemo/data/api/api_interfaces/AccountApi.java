package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.FavoriteMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.data.models.response.RatedMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.UserModel;
import ua.r4mstein.moviedbdemo.data.models.response.WatchlistModel;

public interface AccountApi {

    Observable<GetListsModel> getCreatedLists(String apiKey, String sessionId, long page);

    Observable<UserModel> getAccountDetails(String apiKey, String sessionId);

    Observable<AddMovieToListModel> markAsFavorite(long accountId, String apiKey, String sessionId,
                                                   MarkFavoriteSendModel sendModel);

    Observable<FavoriteMoviesModel> getFavoriteMovies(long accountId, String apiKey, String sessionId, long page);

    Observable<AddMovieToListModel> addToWatchList(long accountId, String apiKey, String sessionId,
                                                   AddToWatchlistSendModel sendModel);

    Observable<WatchlistModel> getWatchlist(long accountId, String apiKey, String sessionId, long page);

    Observable<RatedMoviesModel> getRatedMovies(long accountId, String apiKey, String sessionId, long page);
}
