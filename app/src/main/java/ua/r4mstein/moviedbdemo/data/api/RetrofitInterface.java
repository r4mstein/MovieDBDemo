package ua.r4mstein.moviedbdemo.data.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.r4mstein.moviedbdemo.data.models.response.RequestTokenModel;

public interface RetrofitInterface {

//    LOGIN

    @GET("authentication/token/new")
    Observable<RequestTokenModel> getRequestToken(@Query("api_key") String apiKey);

    @GET("authentication/token/validate_with_login")
    Observable<RequestTokenModel> validateRequestToken(@Query("api_key") String apiKey, @Query("username") String username,
                                      @Query("password") String password, @Query("request_token") String requestToken);

//    *****
}
