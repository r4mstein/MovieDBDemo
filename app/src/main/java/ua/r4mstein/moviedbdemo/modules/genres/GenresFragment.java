package ua.r4mstein.moviedbdemo.modules.genres;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.GenreMovieModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

public class GenresFragment extends BaseFragment<GenresPresenter>
        implements GenresPresenter.GenresView {

    private RecyclerView mRecyclerView;

    @Override
    protected int getTitle() {
        return R.string.title_genres;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_genres;
    }

    @Override
    protected GenresPresenter initPresenter() {
        return new GenresPresenter();
    }

    @Override
    protected void findUI(View rootView) {
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_GF);
    }

    @Override
    protected void setupUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getViewContext()));
    }

    @Override
    public void showResult(GenreMovieModel genreMovieModel) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < genreMovieModel.getGenres().size(); i++) {
            result.add(genreMovieModel.getGenres().get(i).getName());
        }

        GenresAdapter adapter = new GenresAdapter(result);
        mRecyclerView.setAdapter(adapter);
    }
}
