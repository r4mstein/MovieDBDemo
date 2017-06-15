package ua.r4mstein.moviedbdemo.modules.lists.list_details;

import android.widget.TextView;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.ListsProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class ListDetailsPresenter extends BaseFragmentPresenter<ListDetailsPresenter.ListDetailsView> {

    private ListsProvider mListsProvider = new ListsProvider();

    @Override
    public void onViewCreated() {
        super.onViewCreated();
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

    interface ListDetailsView extends FragmentView {

        void setList(List<Movie> list);
        TextView getTVCount();
    }
}
