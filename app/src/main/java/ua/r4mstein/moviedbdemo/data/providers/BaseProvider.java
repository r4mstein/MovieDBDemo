package ua.r4mstein.moviedbdemo.data.providers;

import ua.r4mstein.moviedbdemo.data.api.RestClient;
import ua.r4mstein.moviedbdemo.data.api.RetrofitInterface;

public class BaseProvider {

    protected RetrofitInterface api = RestClient.getInstance().getApi();
}
