package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.MoviesApi;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.PopularMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;

public class MoviesProvider extends BaseProvider implements MoviesApi {

    @Override
    public Observable<AddMovieToListModel> rateMovie(long movieId, String apiKey, String sessionId, RateMovieSendModel sendModel) {
        return api.rateMovie(movieId, apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<MovieDetailsModel> getMovieDetails(long movieId, String apiKey) {
        return api.getMovieDetails(movieId, apiKey);
    }

    @Override
    public Observable<AddMovieToListModel> deleteRating(long movieId, String apiKey, String sessionId) {
        return api.deleteRating(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<PopularMoviesModel> getPopularMovies(String apiKey, long page) {
        return api.getPopularMovies(apiKey, page);
    }
}
