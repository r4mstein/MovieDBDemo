package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.MoviesApi;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStates;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStatesAlternative;
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
    public Observable<MovieAccountStates> getMovieAccountStates(long movieId, String apiKey, String sessionId) {
        return api.getMovieAccountStates(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<MovieAccountStatesAlternative> getMovieAccountStatesAlternative(long movieId, String apiKey, String sessionId) {
        return api.getMovieAccountStatesAlternative(movieId, apiKey, sessionId);
    }

    @Override
    public Observable<AddMovieToListModel> deleteRating(long movieId, String apiKey, String sessionId) {
        return api.deleteRating(movieId, apiKey, sessionId);
    }
}
