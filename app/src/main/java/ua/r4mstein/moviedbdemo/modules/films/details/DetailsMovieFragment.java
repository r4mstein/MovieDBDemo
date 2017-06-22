package ua.r4mstein.moviedbdemo.modules.films.details;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.base.BaseFragment;

public class DetailsMovieFragment extends BaseFragment<DetailsMoviePresenter>
        implements DetailsMoviePresenter.DetailsMovieView {

    private ImageView ivPoster;

    private TextView tvTitle, tvTagline, tvOverview, tvCompanies, tvCountries;
    private TextView tvReleaseDate, tvBudget, tvRevenue, tvVote, tvVoteCount;

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
        ivPoster = (ImageView) rootView.findViewById(R.id.iv_poster_FDM);

        tvTitle = (TextView) rootView.findViewById(R.id.tv_title_FDM);
        tvTagline = (TextView) rootView.findViewById(R.id.tv_tagline_FDM);
        tvOverview = (TextView) rootView.findViewById(R.id.tv_overview_FDM);
        tvCompanies = (TextView) rootView.findViewById(R.id.tv_companies_FDM);
        tvCountries = (TextView) rootView.findViewById(R.id.tv_countries_FDM);
        tvReleaseDate = (TextView) rootView.findViewById(R.id.tv_release_date_FDM);
        tvBudget = (TextView) rootView.findViewById(R.id.tv_budget_FDM);
        tvRevenue = (TextView) rootView.findViewById(R.id.tv_revenue_FDM);
        tvVote = (TextView) rootView.findViewById(R.id.tv_vote_FDM);
        tvVoteCount = (TextView) rootView.findViewById(R.id.tv_vote_count_FDM);
    }

    @Override
    protected void setupUI() {

    }
}
