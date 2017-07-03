package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.SearchApi;
import ua.r4mstein.moviedbdemo.data.models.response.SearchMoviesModel;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleModel;

public class SearchProvider extends BaseProvider implements SearchApi {

    @Override
    public Observable<SearchMoviesModel> searchMovies(String apiKey, String searchRequest, long page) {
        return api.searchMovies(apiKey, searchRequest, page);
    }

    @Override
    public Observable<PopularPeopleModel> searchPersons(String apiKey, String searchRequest, long page) {
        return api.searchPersons(apiKey, searchRequest, page);
    }
}
