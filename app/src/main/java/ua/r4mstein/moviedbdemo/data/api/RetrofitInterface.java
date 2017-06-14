package ua.r4mstein.moviedbdemo.data.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;
import ua.r4mstein.moviedbdemo.data.models.response.MoviesByGenreModel;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.models.response.SessionModel;

public interface RetrofitInterface {

//    LOGIN

    @GET("authentication/token/new")
    Observable<RequestTokenModel> getRequestToken(@Query("api_key") String apiKey);

    @GET("authentication/token/validate_with_login")
    Observable<RequestTokenModel> validateRequestToken(@Query("api_key") String apiKey, @Query("username") String username,
                                      @Query("password") String password, @Query("request_token") String requestToken);

    @GET("authentication/session/new")
    Observable<SessionModel> createSessionId(@Query("api_key") String apiKey, @Query("request_token") String requestToken);

//    *****

//    GENRES

    @GET("genre/movie/list")
    Observable<GenreMovieModel> getGenreMovieList(@Query("api_key") String apiKey);

    @GET("genre/{genre_id}/movies")
    Observable<MoviesByGenreModel> getMoviesByGenre(@Path("genre_id") long genreId, @Query("api_key") String apiKey);

//    *****
}
