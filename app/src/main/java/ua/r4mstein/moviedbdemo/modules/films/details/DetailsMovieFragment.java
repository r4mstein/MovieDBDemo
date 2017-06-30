package ua.r4mstein.moviedbdemo.modules.films.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.DELETE_RATING;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.FAVORITE_WATCHLIST;
import static ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreFragment.SET_RATING;

public class DetailsMovieFragment extends BaseFragment<DetailsMoviePresenter>
        implements DetailsMoviePresenter.DetailsMovieView {

    public static final String MOVIE_ID = "movie_id";

    private RecyclerView mRecyclerView;
    private DetailsMovieAdapter adapter;

    @Override
    protected int getTitle() {
        return R.string.title_details_movie;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_details_movie;
    }

    @Override
    protected DetailsMoviePresenter initPresenter() {
        return new DetailsMoviePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FDM);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new DetailsMovieAdapter(getViewContext());
        adapter.setDetailsMovieClickListener(getDetailsMovieClickListener());
        mRecyclerView.setAdapter(adapter);
    }

    @NonNull
    private DetailsMovieClickListener getDetailsMovieClickListener() {
        return new DetailsMovieClickListener() {
            @Override
            public void moviesItemLongClicked(long movieId) {
                getPresenter().getMovieAccountState(movieId, FAVORITE_WATCHLIST);
            }

            @Override
            public void ratingViewClicked(long movieId) {
                getPresenter().getMovieAccountState(movieId, SET_RATING);
            }

            @Override
            public void ratingViewLongClicked(long movieId) {
                getPresenter().getMovieAccountState(movieId, DELETE_RATING);
            }
        };
    }

    public static DetailsMovieFragment newInstance(long movieId) {
        DetailsMovieFragment fragment = new DetailsMovieFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(MOVIE_ID, movieId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void addList(List<MovieDetailsModel> list) {
        adapter.addData(list);
    }
}
