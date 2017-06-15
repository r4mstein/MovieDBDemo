package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.ListsApi;
import ua.r4mstein.moviedbdemo.data.models.response.ListDetailsModel;

public class ListsProvider extends BaseProvider implements ListsApi {

    @Override
    public Observable<ListDetailsModel> getListDetails(long listId, String apiKey) {
        return api.getListDetails(listId, apiKey);
    }
}
