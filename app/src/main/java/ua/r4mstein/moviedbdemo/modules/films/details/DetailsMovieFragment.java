package ua.r4mstein.moviedbdemo.modules.films.details;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.utills.Logger;
import ua.r4mstein.moviedbdemo.utills.MathManager;

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
        adapter.setDetailsMovieClickListener(new DetailsMovieClickListener() {
            @Override
            public void ratingViewClicked(long movieId) {
                FragmentManager manager = getFragmentManager();

                DialogRating dialogRating = new DialogRating();
                dialogRating.setDialogRatingClickListener(rating -> {
                    float sendRating = MathManager.getRating(rating);
                    Logger.d("positiveClicked: rating = " + sendRating);

                    getPresenter().rateMovie(movieId, dialogRating, sendRating);
                });
                dialogRating.show(manager, "DialogRating");
            }
        });
        mRecyclerView.setAdapter(adapter);
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
