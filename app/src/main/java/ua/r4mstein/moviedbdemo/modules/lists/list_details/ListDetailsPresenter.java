package ua.r4mstein.moviedbdemo.modules.lists.list_details;

import android.widget.TextView;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.request.AddMovieToListSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.providers.ListsProvider;
import ua.r4mstein.moviedbdemo.data.providers.SearchProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.modules.films.search_film.SearchFilmDialog;
import ua.r4mstein.moviedbdemo.modules.lists.get_lists.GetListsFragment;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class ListDetailsPresenter extends BaseFragmentPresenter<ListDetailsPresenter.ListDetailsView> {

    private ListsProvider mListsProvider = new ListsProvider();
    private SearchProvider mSearchProvider = new SearchProvider();

    private long current_page;
    private long total_pages;
    private long listId;
    private long itemCount;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        listId = getArguments().getLong(ListsDetailsFragment.LIST_ID);
        getDetailsFragment(listId);
    }

    private void getDetailsFragment(long listId) {
        execute(mListsProvider.getListDetails(listId, API_KEY),
                listDetailsModel -> {
                    itemCount = listDetailsModel.getItemCount();
                    getView().getTVCount().setText(String.format("%s %s", "Items count:", itemCount));
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

    public void addMovieToList(long movieId) {
        AddMovieToListSendModel sendModel = new AddMovieToListSendModel();
        sendModel.setMediaId(movieId);

        execute(mListsProvider.addMovieToList(listId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> {
                            getView().getSearchFilmDialog().dismiss();
                            getDetailsFragment(listId);
                        }, null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void removeMovieFromList(long movieId) {
        AddMovieToListSendModel sendModel = new AddMovieToListSendModel();
        sendModel.setMediaId(movieId);

        execute(mListsProvider.removeMovieFromList(listId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), sendModel),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> getDetailsFragment(listId), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void showRemoveItemDialog(String title, long movieId) {
        getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                getView().getAppResources().getString(R.string.dialog_delete_message) + title + "?",
                v -> {
                    Logger.d("positive clicked");
                    removeMovieFromList(movieId);
                },
                v -> Logger.d("negative clicked"));
    }

    private void clearList() {
        execute(mListsProvider.clearList(listId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId(), true),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> getDetailsFragment(listId), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void showClearListDialog() {
        if (itemCount == 0) {
            getRouter().showDialog(new InfoDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_empty_message), null, null);
        } else {
            getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                    getView().getAppResources().getString(R.string.dialog_clear_list_message),
                    v -> {
                        Logger.d("positive clicked");
                        clearList();
                    },
                    v -> Logger.d("negative clicked"));
        }
    }

    private void deleteList() {
        execute(mListsProvider.deleteList(listId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> getRouter().replaceFragment(new GetListsFragment(), false), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void showDeleteListDialog() {
        getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                getView().getAppResources().getString(R.string.dialog_delete_list_message),
                v -> {
                    Logger.d("positive clicked");
                    deleteList();
                },
                v -> Logger.d("negative clicked"));
    }

    interface ListDetailsView extends FragmentView {

        void setList(List<Movie> list);

        void setSearchList(List<Movie> list);

        void addSearchList(List<Movie> list);

        TextView getTVCount();

        SearchFilmDialog getSearchFilmDialog();
    }
}
