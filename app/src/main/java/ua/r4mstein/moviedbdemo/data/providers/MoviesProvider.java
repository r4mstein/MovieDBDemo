package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.MoviesApi;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;

public class MoviesProvider extends BaseProvider implements MoviesApi {

    @Override
    public Observable<AddMovieToListModel> rateMovie(long movieId, String apiKey, String sessionId, RateMovieSendModel sendModel) {
        return api.rateMovie(movieId, apiKey, sessionId, sendModel);
    }
}
