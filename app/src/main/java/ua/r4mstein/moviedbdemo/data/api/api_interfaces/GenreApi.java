package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;

public interface GenreApi {

    Observable<GenreMovieModel> getGenreMovieList(String apiKey);
}
