package ua.r4mstein.moviedbdemo.modules.people.popular;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleItem;
import ua.r4mstein.moviedbdemo.data.providers.PeopleProvider;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragmentPresenter;
import ua.r4mstein.moviedbdemo.modules.base.FragmentView;
import ua.r4mstein.moviedbdemo.modules.dialog.KnownForDialog;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.API_KEY;

public class PopularPeoplePresenter extends BaseFragmentPresenter<PopularPeoplePresenter.PopularPeopleView> {

    private PeopleProvider mPeopleProvider = new PeopleProvider();

    private long current_page;
    private long total_pages;

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        current_page = 1;
        getPopularPeople(current_page);
    }

    public void getNextPage() {
        if (current_page < total_pages) getPopularPeople(current_page + 1);
    }

    public void getPopularPeople(long pageNumber) {
        execute(mPeopleProvider.getPopularPeople(API_KEY, pageNumber),
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

    public void showDialog(List<KnownFor> list) {
        FragmentManager manager = getView().getFragManager();
        FragmentTransaction transaction = manager.beginTransaction();

        KnownForDialog dialog = KnownForDialog.newInstance(list);
        dialog.show(transaction, "KnownForDialog");
    }

    interface PopularPeopleView extends FragmentView {
        void setList(List<PopularPeopleItem> list);
        void addList(List<PopularPeopleItem> list);
        FragmentManager getFragManager();
    }
}