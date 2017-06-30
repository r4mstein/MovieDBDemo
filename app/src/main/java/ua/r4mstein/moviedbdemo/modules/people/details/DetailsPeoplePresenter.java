package ua.r4mstein.moviedbdemo.modules.people.details;

import ua.r4mstein.moviedbdemo.data.models.response.PersonDetailsModel;
import ua.r4mstein.moviedbdemo.data.providers.PeopleProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class DetailsPeoplePresenter extends BaseFragmentPresenter<DetailsPeoplePresenter.DetailsPeopleView> {

    private PeopleProvider mPeopleProvider = new PeopleProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        long personId = getArguments().getLong(DetailsPeopleFragment.PERSON_ID);
        getPersonDetails(personId);
    }

    private void getPersonDetails(long personId) {
        execute(mPeopleProvider.getPersonDetails(personId, API_KEY),
                model -> {
                    getView().fillData(model);
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface DetailsPeopleView extends FragmentView {

        void fillData(PersonDetailsModel model);
    }
}
