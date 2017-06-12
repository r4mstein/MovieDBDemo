package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;
import ua.r4mstein.moviedbdemo.data.models.response.SessionModel;

public interface LoginApi {

    Observable<RequestTokenModel> getRequestToken(String apiKey);

    Observable<RequestTokenModel> validateRequestToken(String apiKey, String username,
                                                       String password, String requestToken);

    Observable<SessionModel> createSessionId(String apiKey, String requestToken);
}
