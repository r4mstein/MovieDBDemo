package ua.r4mstein.moviedbdemo.data.providers;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.api.api_interfaces.PeopleApi;
import ua.r4mstein.moviedbdemo.data.models.response.PersonDetailsModel;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleModel;

public class PeopleProvider extends BaseProvider implements PeopleApi {

    @Override
    public Observable<PopularPeopleModel> getPopularPeople(String apiKey, long page) {
        return api.getPopularPeople(apiKey, page);
    }

    @Override
    public Observable<PersonDetailsModel> getPersonDetails(long personID, String apiKey) {
        return api.getPersonDetails(personID, apiKey);
    }
}
