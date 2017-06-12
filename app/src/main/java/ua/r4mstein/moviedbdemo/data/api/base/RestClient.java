package ua.r4mstein.moviedbdemo.data.api.base;

import ua.r4mstein.moviedbdemo.data.api.RetrofitInterface;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class RestClient {

    private static volatile RestClient instance;
    private RetrofitInterface mApi;
    private IRestAdapter adapter;

    private RestClient() {
        setupAdapter();
    }

    public static RestClient getInstance() {
        RestClient localInstance = instance;
        if (localInstance == null) {
            synchronized (RestClient.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RestClient();
                }
            }
        }
        return localInstance;
    }

    public void setupAdapter() {
        adapter = new RetrofitAdapter();
        mApi = adapter.createApi(RetrofitInterface.class, Constants.BASE_URL + Constants.API_VERSION);
    }

    public RetrofitInterface getApi() {
        return getInstance().mApi;
    }
}
