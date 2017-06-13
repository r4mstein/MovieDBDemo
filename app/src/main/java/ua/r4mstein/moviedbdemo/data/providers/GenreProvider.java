package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.GenreApi;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;

public class GenreProvider extends BaseProvider implements GenreApi {

    @Override
    public Observable<GenreMovieModel> getGenreMovieList(String apiKey) {
        return api.getGenreMovieList(apiKey);
    }
}
