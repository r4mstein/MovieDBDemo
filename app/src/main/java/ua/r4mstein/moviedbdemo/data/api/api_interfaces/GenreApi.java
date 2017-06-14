package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;
import ua.r4mstein.moviedbdemo.data.models.response.MoviesByGenreModel;

public interface GenreApi {

    Observable<GenreMovieModel> getGenreMovieList(String apiKey);

    Observable<MoviesByGenreModel> getMoviesByGenre(long genreId, String apiKey, long page);
}
