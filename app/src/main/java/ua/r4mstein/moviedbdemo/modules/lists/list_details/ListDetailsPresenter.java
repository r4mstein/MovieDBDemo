package ua.r4mstein.moviedbdemo.modules.lists.list_details;

import android.widget.TextView;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.models.response.SearchMoviesModel;
import ua.r4mstein.moviedbdemo.data.providers.ListsProvider;
import ua.r4mstein.moviedbdemo.data.providers.SearchProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class ListDetailsPresenter extends BaseFragmentPresenter<ListDetailsPresenter.ListDetailsView> {

    private ListsProvider mListsProvider = new ListsProvider();
    private SearchProvider mSearchProvider = new SearchProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getDetailsFragment(getArguments().getLong(ListsDetailsFragment.LIST_ID));
    }

    private void getDetailsFragment(long listId) {
        execute(mListsProvider.getListDetails(listId, API_KEY),
                listDetailsModel -> {
                    getView().getTVCount().setText(String.format("%s %s", "Items count:", listDetailsModel.getItemCount()));
                    getView().setList(listDetailsModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void getSearchMovies(String searchRequest, long pageNumber) {
        execute(mSearchProvider.searchMovies(API_KEY, searchRequest, pageNumber),
                searchMoviesModel -> {
                    current_page = pageNumber;
                    total_pages = searchMoviesModel.getTotalPages();

                    if (current_page == 1)
                        getView().setSearchList(searchMoviesModel.getMovies());
                    else
                        getView().addSearchList(searchMoviesModel.getMovies());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void getNextSearchPage(String searchRequest) {
        if (current_page < total_pages) getSearchMovies(searchRequest, current_page + 1);
    }

    interface ListDetailsView extends FragmentView {

        void setList(List<Movie> list);
        void setSearchList(List<Movie> list);
        void addSearchList(List<Movie> list);
        TextView getTVCount();
    }
}
