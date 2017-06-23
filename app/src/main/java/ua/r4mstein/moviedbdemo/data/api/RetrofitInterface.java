package ua.r4mstein.moviedbdemo.data.api;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.r4mstein.moviedbdemo.data.models.request.AddMovieToListSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.AddToWatchlistSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.CreateListSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.MarkFavoriteSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.CreateListModel;
import ua.r4mstein.moviedbdemo.data.models.response.FavoriteMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.data.models.response.ListDetailsModel;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStates;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStatesAlternative;
import ua.r4mstein.moviedbdemo.data.models.response.MoviesByGenreModel;
import ua.r4mstein.moviedbdemo.data.models.response.RatedMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.models.response.SearchMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.SessionModel;
import ua.r4mstein.moviedbdemo.data.models.response.UserModel;
import ua.r4mstein.moviedbdemo.data.models.response.WatchlistModel;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;

public interface RetrofitInterface {

//    LOGIN

    @GET("authentication/token/new")
    Observable<RequestTokenModel> getRequestToken(@Query("api_key") String apiKey);

    @GET("authentication/token/validate_with_login")
    Observable<RequestTokenModel> validateRequestToken(@Query("api_key") String apiKey,
                                                       @Query("username") String username,
                                                       @Query("password") String password,
                                                       @Query("request_token") String requestToken);

    @GET("authentication/session/new")
    Observable<SessionModel> createSessionId(@Query("api_key") String apiKey,
                                             @Query("request_token") String requestToken);

//    *****

//    GENRES

    @GET("genre/movie/list")
    Observable<GenreMovieModel> getGenreMovieList(@Query("api_key") String apiKey);

    @GET("genre/{genre_id}/movies")
    Observable<MoviesByGenreModel> getMoviesByGenre(@Path("genre_id") long genreId,
                                                    @Query("api_key") String apiKey,
                                                    @Query("page") long page);

//    *****

//    ACCOUNT

    @GET("account/{account_id}/lists")
    Observable<GetListsModel> getCreatedLists(@Query("api_key") String apiKey,
                                              @Query("session_id") String sessionId,
                                              @Query("page") long page);

    @GET("account")
    Observable<UserModel> getAccountDetails(@Query("api_key") String apiKey,
                                            @Query("session_id") String sessionId);

    @POST("account/{account_id}/favorite")
    Observable<AddMovieToListModel> markAsFavorite(@Path("account_id") long accountId,
                                                   @Query("api_key") String apiKey,
                                                   @Query("session_id") String sessionId,
                                                   @Body MarkFavoriteSendModel sendModel);

    @GET("account/{account_id}/favorite/movies")
    Observable<FavoriteMoviesModel> getFavoriteMovies(@Path("account_id") long accountId,
                                                      @Query("api_key") String apiKey,
                                                      @Query("session_id") String sessionId,
                                                      @Query("page") long page);

    @POST("account/{account_id}/watchlist")
    Observable<AddMovieToListModel> addToWatchList(@Path("account_id") long accountId,
                                                   @Query("api_key") String apiKey,
                                                   @Query("session_id") String sessionId,
                                                   @Body AddToWatchlistSendModel sendModel);

    @GET("account/{account_id}/watchlist/movies")
    Observable<WatchlistModel> getWatchlist(@Path("account_id") long accountId,
                                            @Query("api_key") String apiKey,
                                            @Query("session_id") String sessionId,
                                            @Query("page") long page);

    @GET("account/{account_id}/rated/movies")
    Observable<RatedMoviesModel> getRatedMovies(@Path("account_id") long accountId,
                                                @Query("api_key") String apiKey,
                                                @Query("session_id") String sessionId,
                                                @Query("page") long page);


//    *****

//    LISTS

    @GET("list/{list_id}")
    Observable<ListDetailsModel> getListDetails(@Path("list_id") long listId,
                                                @Query("api_key") String apiKey);

    @POST("list")
    Observable<CreateListModel> createList(@Query("api_key") String apiKey,
                                           @Query("session_id") String sessionId,
                                           @Body CreateListSendModel sendModel);

    @POST("list/{list_id}/add_item")
    Observable<AddMovieToListModel> addMovieToList(@Path("list_id") long listId,
                                                   @Query("api_key") String apiKey,
                                                   @Query("session_id") String sessionId,
                                                   @Body AddMovieToListSendModel sendModel);

    @POST("list/{list_id}/remove_item")
    Observable<AddMovieToListModel> removeMovieFromList(@Path("list_id") long listId,
                                                        @Query("api_key") String apiKey,
                                                        @Query("session_id") String sessionId,
                                                        @Body AddMovieToListSendModel sendModel);

    @POST("list/{list_id}/clear")
    Observable<AddMovieToListModel> clearList(@Path("list_id") long listId,
                                              @Query("api_key") String apiKey,
                                              @Query("session_id") String sessionId,
                                              @Query("confirm") boolean confirm);

    @DELETE("list/{list_id}")
    Observable<AddMovieToListModel> deleteList(@Path("list_id") long listId,
                                               @Query("api_key") String apiKey,
                                               @Query("session_id") String sessionId);

//    *****

//        SEARCH

    @GET("search/movie")
    Observable<SearchMoviesModel> searchMovies(@Query("api_key") String apiKey,
                                               @Query("query") String searchRequest,
                                               @Query("page") long page);

//    *****

//    MOVIES

    @POST("movie/{movie_id}/rating")
    Observable<AddMovieToListModel> rateMovie(@Path("movie_id") long movieId,
                                              @Query("api_key") String apiKey,
                                              @Query("session_id") String sessionId,
                                              @Body RateMovieSendModel sendModel);

    @GET("movie/{movie_id}")
    Observable<MovieDetailsModel> getMovieDetails(@Path("movie_id") long movieId,
                                                  @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/account_states")
    Observable<MovieAccountStates> getMovieAccountStates(@Path("movie_id") long movieId,
                                                         @Query("api_key") String apiKey,
                                                         @Query("session_id") String sessionId);

    @GET("movie/{movie_id}/account_states")
    Observable<MovieAccountStatesAlternative> getMovieAccountStatesAlternative(@Path("movie_id") long movieId,
                                                                               @Query("api_key") String apiKey,
                                                                               @Query("session_id") String sessionId);

    @DELETE("movie/{movie_id}/rating")
    Observable<AddMovieToListModel> deleteRating(@Path("movie_id") long movieId,
                                                 @Query("api_key") String apiKey,
                                                 @Query("session_id") String sessionId);

//    *****
}
