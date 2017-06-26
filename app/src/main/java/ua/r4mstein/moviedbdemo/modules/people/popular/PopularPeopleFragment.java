package ua.r4mstein.moviedbdemo.modules.people.popular;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleItem;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;

public class PopularPeopleFragment extends BaseFragment<PopularPeoplePresenter>
        implements PopularPeoplePresenter.PopularPeopleView {

    private RecyclerView mRecyclerView;
    private PopularPeopleAdapter mAdapter;

    @Override
    protected int getTitle() {
        return R.string.title_popular_people;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_popular_people;
    }

    @Override
    protected PopularPeoplePresenter initPresenter() {
        return new PopularPeoplePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FPP);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new PopularPeopleAdapter(getViewContext());
        mAdapter.setPopularPeopleClickListener(getPopularPeopleClickListener());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage();
                    return true;
                }));
    }

    @NonNull
    private PopularPeopleClickListener getPopularPeopleClickListener() {
        return new PopularPeopleClickListener() {
            @Override
            public void popularPeopleItemClicked(long movieId) {

            }

            @Override
            public void popularPeopleItemLongClicked(long movieId) {

            }

            @Override
            public void knownForClicked(List<KnownFor> list) {
                getPresenter().showDialog(list);
            }
        };
    }

    @Override
    public void setList(List<PopularPeopleItem> list) {
        mAdapter.setData(list);
    }

    @Override
    public void addList(List<PopularPeopleItem> list) {
        mAdapter.addData(list);
    }
}
