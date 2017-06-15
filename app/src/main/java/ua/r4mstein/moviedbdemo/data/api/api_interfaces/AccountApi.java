package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;

public interface AccountApi {

    Observable<GetListsModel> getCreatedLists(String apiKey, String sessionId, long page);
}
