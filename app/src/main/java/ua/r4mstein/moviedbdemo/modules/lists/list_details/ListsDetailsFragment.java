package ua.r4mstein.moviedbdemo.modules.lists.list_details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreAdapter;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesClickListener;
import ua.r4mstein.moviedbdemo.modules.films.search_film.OnSearchClickListener;
import ua.r4mstein.moviedbdemo.modules.films.search_film.SearchFilmDialog;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;
import ua.r4mstein.moviedbdemo.utills.Logger;

public class ListsDetailsFragment extends BaseFragment<ListDetailsPresenter>
        implements ListDetailsPresenter.ListDetailsView {

    public static final String LIST_ID = "list_id";

    private TextView tvCount;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFAB;
    private ListDetailsAdapter mAdapter;
    private MoviesByGenreAdapter adapter;
    private SearchFilmDialog filmDialog;

    private int startSearchPage = 1;

    @Override
    protected int getTitle() {
        return R.string.title_list_details;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_list_details;
    }

    @Override
    protected ListDetailsPresenter initPresenter() {
        return new ListDetailsPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        tvCount = (TextView) rootView.findViewById(R.id.tv_count_FLD);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FLD);
        mFAB = (FloatingActionButton) rootView.findViewById(R.id.fab_FLD);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListDetailsAdapter(getViewContext());
        mAdapter.setListDetailsClickListener(new ListDetailsClickListener() {
            @Override
            public void itemClicked(long movieId) {
                Logger.d("itemClicked: movieId = " + movieId);
            }

            @Override
            public void itemLongClicked(long movieId) {
                Logger.d("itemClicked: movieId = " + movieId);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mFAB.setOnClickListener(getFABClickListener());
    }

    @NonNull
    private View.OnClickListener getFABClickListener() {
        return v -> {
            FragmentManager manager = getFragmentManager();

            filmDialog = SearchFilmDialog.newInstance("Search Movie");
            filmDialog.setClickListener(getOnSearchClickListener());

            filmDialog.show(manager, "filmDialog");
        };
    }

    @NonNull
    private OnSearchClickListener getOnSearchClickListener() {
        return (searchRequest, recyclerView) -> {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);

            adapter = new MoviesByGenreAdapter(getViewContext());
            adapter.setMoviesClickListener(new MoviesClickListener() {
                @Override
                public void moviesItemClicked(long movieId) {
                    getPresenter().addMovieToList(movieId);
                }

                @Override
                public void moviesItemLongClicked(long movieId) {

                }
            });
            recyclerView.setAdapter(adapter);

            recyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                    () -> {
                        getPresenter().getNextSearchPage(searchRequest);
                        return true;
                    }));

            getPresenter().getSearchMovies(searchRequest, startSearchPage);
        };
    }

    public static ListsDetailsFragment newInstance(long listId) {
        ListsDetailsFragment fragment = new ListsDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(LIST_ID, listId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void setList(List<Movie> list) {
        mAdapter.setData(list);
    }

    @Override
    public void setSearchList(List<Movie> list) {
        adapter.setData(list);
    }

    @Override
    public void addSearchList(List<Movie> list) {
        adapter.addData(list);
    }

    @Override
    public TextView getTVCount() {
        return tvCount;
    }

    @Override
    public SearchFilmDialog getSearchFilmDialog() {
        return filmDialog;
    }
}
