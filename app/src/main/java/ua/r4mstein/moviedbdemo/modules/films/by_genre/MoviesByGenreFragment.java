package ua.r4mstein.moviedbdemo.modules.films.by_genre;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.data.models.response.MoviesByGenreModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

public class MoviesByGenreFragment extends BaseFragment<MoviesByGenrePresenter>
        implements MoviesByGenrePresenter.MoviesByGenreView {

    public static final String GENRE_ID = "genre_id";

    private RecyclerView mRecyclerView;

    @Override
    protected int getTitle() {
        return R.string.title_movies_by_genre;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_movies_by_genre;
    }

    @Override
    protected MoviesByGenrePresenter initPresenter() {
        return new MoviesByGenrePresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FMBG);
    }

    @Override
    protected void setupUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static MoviesByGenreFragment newInstance(long genreId) {
        MoviesByGenreFragment fragment = new MoviesByGenreFragment();

        Bundle bundle = new Bundle();
        bundle.putLong(GENRE_ID, genreId);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void showResult(MoviesByGenreModel model) {
        List<Movie> movieList = model.getMovies();

        MoviesByGenreAdapter adapter = new MoviesByGenreAdapter(movieList, getViewContext());
        mRecyclerView.setAdapter(adapter);
    }
}
