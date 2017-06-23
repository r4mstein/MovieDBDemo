package ua.r4mstein.moviedbdemo.modules.films.popular_movies;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesByGenreAdapter;
import ua.r4mstein.moviedbdemo.modules.films.by_genre.MoviesClickListener;
import ua.r4mstein.moviedbdemo.utills.EndlessScrollListener;

public class PopularMoviesFragment extends BaseFragment<PopularMoviesPresenter>
        implements PopularMoviesPresenter.PopularMoviesView {

    private RecyclerView mRecyclerView;
    private MoviesByGenreAdapter adapter;

    @Override
    protected int getTitle() {
        return R.string.title_popular_movies;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_popular_movies;
    }

    @Override
    protected PopularMoviesPresenter initPresenter() {
        return new PopularMoviesPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_FPM);
    }

    @Override
    protected void setupUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesByGenreAdapter(getViewContext());
        adapter.setMoviesClickListener(getMoviesClickListener());
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnScrollListener(new EndlessScrollListener(layoutManager,
                () -> {
                    getPresenter().getNextPage();
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

            }

            @Override
            public void ratingViewClicked(long movieId, float oldRating) {

            }

            @Override
            public void ratingViewLongClicked(long movieId) {

            }
        };
    }

    @Override
    public void setList(List<Movie> list) {
        adapter.setData(list);
    }

    @Override
    public void addList(List<Movie> list) {
        adapter.addData(list);
    }
}
