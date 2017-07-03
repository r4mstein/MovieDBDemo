package ua.r4mstein.moviedbdemo.modules.people.search_people;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleItem;
import ua.r4mstein.moviedbdemo.data.providers.SearchProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class SearchPeoplePresenter extends BaseFragmentPresenter<SearchPeoplePresenter.SearchPeopleView> {

    private SearchProvider mSearchProvider = new SearchProvider();

    private long current_page;
    private long total_pages;

    public void getNextPage(String searchRequest) {
        if (current_page < total_pages) getSearchPeople(current_page + 1, searchRequest);
    }

    public void getSearchPeople(long pageNumber, String searchRequest) {
        execute(mSearchProvider.searchPersons(API_KEY, searchRequest, pageNumber),
                popularPeopleModel -> {
                    current_page = pageNumber;
                    total_pages = popularPeopleModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(popularPeopleModel.getItems());
                    else
                        getView().addList(popularPeopleModel.getItems());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    interface SearchPeopleView extends FragmentView {
        void setList(List<PopularPeopleItem> list);
        void addList(List<PopularPeopleItem> list);
    }
}
