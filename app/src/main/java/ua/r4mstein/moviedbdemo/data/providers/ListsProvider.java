package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.ListsApi;
import ua.r4mstein.moviedbdemo.data.models.request.AddMovieToListSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.CreateListSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.CreateListModel;
import ua.r4mstein.moviedbdemo.data.models.response.ListDetailsModel;

public class ListsProvider extends BaseProvider implements ListsApi {

    @Override
    public Observable<ListDetailsModel> getListDetails(long listId, String apiKey) {
        return api.getListDetails(listId, apiKey);
    }

    @Override
    public Observable<CreateListModel> createList(String apiKey, String sessionId, CreateListSendModel sendModel) {
        return api.createList(apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<AddMovieToListModel> addMovieToList(long listId, String apiKey, String sessionId, AddMovieToListSendModel sendModel) {
        return api.addMovieToList(listId, apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<AddMovieToListModel> removeMovieFromList(long listId, String apiKey, String sessionId, AddMovieToListSendModel sendModel) {
        return api.removeMovieFromList(listId, apiKey, sessionId, sendModel);
    }

    @Override
    public Observable<AddMovieToListModel> clearList(long listId, String apiKey, String sessionId, boolean confirm) {
        return api.clearList(listId, apiKey, sessionId, confirm);
    }
}
