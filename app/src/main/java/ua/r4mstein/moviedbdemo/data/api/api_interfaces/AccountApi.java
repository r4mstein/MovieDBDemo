package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.data.models.response.UserModel;

public interface AccountApi {

    Observable<GetListsModel> getCreatedLists(String apiKey, String sessionId, long page);

    Observable<UserModel> getAccountDetails(String apiKey, String sessionId);
}
