package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;

public interface MoviesApi {

    Observable<AddMovieToListModel> rateMovie(long movieId, String apiKey, String sessionId,
                                              RateMovieSendModel sendModel);

    Observable<MovieDetailsModel> getMovieDetails(long movieId, String apiKey);
}
