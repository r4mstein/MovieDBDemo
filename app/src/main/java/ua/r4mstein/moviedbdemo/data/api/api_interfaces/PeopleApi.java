package ua.r4mstein.moviedbdemo.data.api.api_interfaces;

import io.reactivex.Observable;
import ua.r4mstein.moviedbdemo.data.models.response.PersonDetailsModel;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleModel;

public interface PeopleApi {

    Observable<PopularPeopleModel> getPopularPeople(String apiKey, long page);

    Observable<PersonDetailsModel> getPersonDetails(long personID, String apiKey);
}
