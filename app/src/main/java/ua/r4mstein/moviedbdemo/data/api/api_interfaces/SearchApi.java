package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.SearchMoviesModel;

public interface SearchApi {

    Observable<SearchMoviesModel> searchMovies(String apiKey, String searchRequest, long page);
}
