package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.request.CreateListSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.CreateListModel;
import ua.r4mstein.moviedbdemo.data.models.response.ListDetailsModel;

public interface ListsApi {

    Observable<ListDetailsModel> getListDetails(long listId, String apiKey);

    Observable<CreateListModel> createList(String apiKey, String sessionId, CreateListSendModel sendModel);
}
