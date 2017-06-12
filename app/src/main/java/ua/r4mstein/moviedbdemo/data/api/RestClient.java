package ua.r4mstein.moviedbdemo.data.api;

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
        mApi = adapter.createApi(RetrofitInterface.class, Constants.BASE_URL);
    }

    public RetrofitInterface getApi() {
        return getInstance().mApi;
    }
}
