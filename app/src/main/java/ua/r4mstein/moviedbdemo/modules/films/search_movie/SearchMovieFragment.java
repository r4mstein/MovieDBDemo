package ua.r4mstein.moviedbdemo.modules.films.search_movie;

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
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreAdapter;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesClickListener;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;

import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.DELETE_RATING;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.FAVORITE_WATCHLIST;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.SET_RATING;

public class SearchMovieFragment extends BaseFragment<SearchMoviePresenter>
        implements SearchMoviePresenter.SearchMovieView {

    private TextInputLayout tilContainer;
    private TextInputEditText tietSearchRequest;
    private TextView tvSearch;
    private RecyclerView mRecyclerView;

    private MoviesByGenreAdapter mAdapter;
    private long start_page = 1;

    @Override
    protected int getTitle() {
        return R.string.title_search_movies;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search_movie;
    }

    @Override
    protected SearchMoviePresenter initPresenter() {
        return new SearchMoviePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        tilContainer = (TextInputLayout) rootView.findViewById(R.id.til_search_container_FSM);
        tietSearchRequest = (TextInputEditText) rootView.findViewById(R.id.tiet_search_request_FSM);
        tvSearch = (TextView) rootView.findViewById(R.id.tv_search_FSM);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FSM);
    }

    @Override
    protected void setupUI() {
        tvSearch.setOnClickListener(v -> {
            String searchRequest = tietSearchRequest.getText().toString();

            if (!TextUtils.isEmpty(searchRequest)) {
                tilContainer.setErrorEnabled(false);
                getPresenter().getSearchMovies(start_page, searchRequest);
            } else {
                tilContainer.setErrorEnabled(true);
                tilContainer.setError("Please enter Search Request");
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MoviesByGenreAdapter(getViewContext());
        mAdapter.setMoviesClickListener(getMoviesClickListener());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage(tietSearchRequest.getText().toString());
                    return true;
                }));
    }

    @NonNull
    private MoviesClickListener getMoviesClickListener() {
        return new MoviesClickListener() {
            @Override
            public void moviesItemClicked(long movieId) {
                getPresenter().goToDetailScreen(movieId);
            }

            @Override
            public void moviesItemLongClicked(long movieId, int position) {
                getPresenter().getMovieAccountState(movieId, FAVORITE_WATCHLIST);
            }

            @Override
            public void ratingViewClicked(long movieId, float rating) {
                getPresenter().getMovieAccountState(movieId, SET_RATING);
            }

            @Override
            public void ratingViewLongClicked(long movieId) {
                getPresenter().getMovieAccountState(movieId, DELETE_RATING);
            }
        };
    }

    @Override
    public void setList(List<Movie> list) {
        mAdapter.setData(list);
    }

    @Override
    public void addList(List<Movie> list) {
        mAdapter.addData(list);
    }
}
