package ua.r4mstein.moviedbdemo.data.api.base;

import okhttp3.HttpUrl;
import okhttp3.Request;
import ua.r4mstein.moviedbdemo.utills.Constants;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_VERSION;
import static ua.r4mstein.moviedbdemo.utills.Constants.BASE_URL;

public class HttpManager {

    public String getStateURL(Long id) {
        return BASE_URL + API_VERSION + "movie/" + id + "/account_states";
    }

    public Request getRequestInstance(long id) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(getStateURL(id)).newBuilder();
        urlBuilder.addQueryParameter("api_key", Constants.API_KEY);
        urlBuilder.addQueryParameter("session_id", SharedPrefManager.getInstance().retrieveSessionId());
        String url = urlBuilder.build().toString();
        Logger.d(url);

        return new Request.Builder()
                .url(url)
                .build();
    }
}
