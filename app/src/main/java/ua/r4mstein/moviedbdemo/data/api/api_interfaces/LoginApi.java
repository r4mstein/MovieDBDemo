package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;

public interface LoginApi {

    Observable<RequestTokenModel> getRequestToken(String apiKey);
}
