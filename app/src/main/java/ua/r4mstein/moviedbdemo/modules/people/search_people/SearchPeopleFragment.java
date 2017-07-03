package ua.r4mstein.moviedbdemo.modules.people.search_people;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleItem;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.people.popular.PopularPeopleAdapter;
import ua.r4mstein.moviedbdemo.modules.people.popular.PopularPeopleClickListener;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;

public class SearchPeopleFragment extends BaseFragment<SearchPeoplePresenter>
        implements SearchPeoplePresenter.SearchPeopleView {

    private TextInputLayout tilContainer;
    private TextInputEditText tietSearchRequest;
    private TextView tvSearch;
    private RecyclerView mRecyclerView;

    private PopularPeopleAdapter mAdapter;
    private long start_page = 1;

    @Override
    protected int getTitle() {
        return R.string.title_search_people;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_people;
    }

    @Override
    protected SearchPeoplePresenter initPresenter() {
        return new SearchPeoplePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        tilContainer = (TextInputLayout) rootView.findViewById(R.id.til_search_container_FSP);
        tietSearchRequest = (TextInputEditText) rootView.findViewById(R.id.tiet_search_request_FSP);
        tvSearch = (TextView) rootView.findViewById(R.id.tv_search_FSP);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FSP);
    }

    @Override
    protected void setupUI() {
        tvSearch.setOnClickListener(v -> {
            String searchRequest = tietSearchRequest.getText().toString();

            if (!TextUtils.isEmpty(searchRequest)) {
                tilContainer.setErrorEnabled(false);
                getPresenter().getSearchPeople(start_page, searchRequest);
            } else {
                tilContainer.setErrorEnabled(true);
                tilContainer.setError("Please enter Search Request");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new PopularPeopleAdapter(getViewContext());
        mAdapter.setPopularPeopleClickListener(getPopularPeopleClickListener());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage(tietSearchRequest.getText().toString());
                    return true;
                }));
    }

    @NonNull
    private PopularPeopleClickListener getPopularPeopleClickListener() {
        return new PopularPeopleClickListener() {
            @Override
            public void popularPeopleItemClicked(long movieId) {
                getPresenter().goToPersonDetailScreen(movieId);
            }

            @Override
            public void popularPeopleItemLongClicked(long movieId) {

            }

            @Override
            public void knownForClicked(List<KnownFor> list) {
                getPresenter().showKnownForDialog(list);
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
