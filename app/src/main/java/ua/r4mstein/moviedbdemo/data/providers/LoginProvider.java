package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.LoginApi;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;

public class LoginProvider extends BaseProvider implements LoginApi {

    @Override
    public Observable<RequestTokenModel> getRequestToken(String apiKey) {
        return api.getRequestToken(apiKey);
    }

    @Override
    public Observable<RequestTokenModel> validateRequestToken(String apiKey, String username, String password, String requestToken) {
        return api.validateRequestToken(apiKey, username, password, requestToken);
    }
}
