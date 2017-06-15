package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.AccountApi;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;

public class AccountProvider extends BaseProvider implements AccountApi {

    @Override
    public Observable<GetListsModel> getCreatedLists(String apiKey, String sessionId, long page) {
        return api.getCreatedLists(apiKey, sessionId, page);
    }
}
