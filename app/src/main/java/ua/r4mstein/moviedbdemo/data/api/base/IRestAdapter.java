package ua.r4mstein.moviedbdemo.data.api.base;


public interface IRestAdapter {

    <T> T createApi(Class<T> clazz, String base_url);

}
