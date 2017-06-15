package ua.r4mstein.moviedbdemo.modules.lists;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;

public class GetListsFragment extends BaseFragment<GetListsPresenter>
        implements GetListsPresenter.GetListsView {

    private RecyclerView mRecyclerView;
    private GetListsAdapter mAdapter;

    @Override
    protected int getTitle() {
        return R.string.title_get_lists;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_get_lists;
    }

    @Override
    protected GetListsPresenter initPresenter() {
        return new GetListsPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FGL);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new GetListsAdapter();
        mAdapter.setActionListener(getPresenter().getListsActionListener());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage();
                    return true;
                }));
    }

    @Override
    public void setList(List<GetListsModel.Result> list) {
        mAdapter.setData(list);
    }

    @Override
    public void addList(List<GetListsModel.Result> list) {
        mAdapter.addData(list);
    }
}
