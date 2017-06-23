package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.r4mstein.moviedbdemo.data.models.request.RateMovieSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStates;
import ua.r4mstein.moviedbdemo.data.models.response.MovieAccountStatesAlternative;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;

public interface MoviesApi {

    Observable<AddMovieToListModel> rateMovie(long movieId, String apiKey, String sessionId,
                                              RateMovieSendModel sendModel);

    Observable<MovieDetailsModel> getMovieDetails(long movieId, String apiKey);

    Observable<MovieAccountStates> getMovieAccountStates(long movieId, String apiKey, String sessionId);

    Observable<MovieAccountStatesAlternative> getMovieAccountStatesAlternative(long movieId, String apiKey, String sessionId);
}
