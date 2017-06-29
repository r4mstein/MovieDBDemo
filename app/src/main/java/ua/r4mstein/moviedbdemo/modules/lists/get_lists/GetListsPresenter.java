package ua.r4mstein.moviedbdemo.modules.lists.get_lists;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.request.CreateListSendModel;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.data.providers.AccountProvider;
import ua.r4mstein.moviedbdemo.data.providers.ListsProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.dialog.InfoDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.QuestionDialog;
import ua.r4mstein.moviedbdemo.modules.lists.list_details.ListsDetailsFragment;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.SharedPrefManager;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class GetListsPresenter extends BaseFragmentPresenter<GetListsPresenter.GetListsView> {

    private AccountProvider mAccountProvider = new AccountProvider();
    private ListsProvider mListsProvider = new ListsProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        getInitialLists();
    }

    public void getNextPage() {
        if (current_page < total_pages) getLists(current_page + 1);
    }

    public void getLists(long pageNumber) {
        execute(mAccountProvider.getCreatedLists(API_KEY,
                SharedPrefManager.getInstance().retrieveSessionId(), pageNumber),
                getListsModel -> {
                    current_page = pageNumber;
                    total_pages = getListsModel.getTotalPages();

                    if (current_page == 1)
                        getView().setList(getListsModel.getResults());
                    else
                        getView().addList(getListsModel.getResults());
                },
                throwable -> Logger.d(throwable.getMessage()));
    }

    public void createList(String name, String description) {
        CreateListSendModel listSendModel = new CreateListSendModel();
        listSendModel.setName(name);
        listSendModel.setDescription(description);
        listSendModel.setLanguage("en");

        execute(mListsProvider.createList(API_KEY, SharedPrefManager.getInstance().retrieveSessionId(),
                listSendModel),
                createListModel -> getRouter().showDialog(new InfoDialog(), R.string.create_list_title,
                        createListModel.getStatusMessage(), v -> getInitialLists(), null),
                throwable -> Logger.d(throwable.getMessage()));
    }

    private void getInitialLists() {
        current_page = 1;
        getLists(current_page);
    }

    public GetListsActionListener getListsActionListener() {
        return new GetListsActionListener() {
            @Override
            public void getListsItemClicked(long listId) {
                GetListsPresenter.this.getRouter().replaceFragment(ListsDetailsFragment.newInstance(listId), false);
            }

            @Override
            public void getListsItemLongClicked(long listId) {
                showDeleteListDialog(listId);
            }
        };
    }

    private void showDeleteListDialog(long listId) {
        getRouter().showQuestionDialog(new QuestionDialog(), R.string.app_name,
                getView().getAppResources().getString(R.string.dialog_delete_list_message),
                v -> {
                    Logger.d("positive clicked");
                    deleteList(listId);
                },
                v -> Logger.d("negative clicked"));
    }

    private void deleteList(long listId) {
        execute(mListsProvider.deleteList(listId, API_KEY, SharedPrefManager.getInstance().retrieveSessionId()),
                addMovieToListModel -> getRouter().showDialog(new InfoDialog(), R.string.app_name, addMovieToListModel.getStatusMessage(),
                        v -> getInitialLists(), null),
                Throwable::getMessage);
    }

    interface GetListsView extends FragmentView {

        void setList(List<GetListsModel.Result> list);

        void addList(List<GetListsModel.Result> list);
    }
}
