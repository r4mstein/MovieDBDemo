package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.request.AddMovieToListSendModel;
import ua.r4mstein.moviedbdemo.data.models.request.CreateListSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.AddMovieToListModel;
import ua.r4mstein.moviedbdemo.data.models.response.CreateListModel;
import ua.r4mstein.moviedbdemo.data.models.response.ListDetailsModel;

public interface ListsApi {

    Observable<ListDetailsModel> getListDetails(long listId, String apiKey);

    Observable<CreateListModel> createList(String apiKey, String sessionId, CreateListSendModel sendModel);

    Observable<AddMovieToListModel> addMovieToList(long listId, String apiKey, String sessionId,
                                                   AddMovieToListSendModel sendModel);

    Observable<AddMovieToListModel> removeMovieFromList(long listId, String apiKey, String sessionId,
                                                   AddMovieToListSendModel sendModel);

    Observable<AddMovieToListModel> clearList(long listId, String apiKey, String sessionId, boolean confirm);

    Observable<AddMovieToListModel> deleteList(long listId, String apiKey, String sessionId);
}
