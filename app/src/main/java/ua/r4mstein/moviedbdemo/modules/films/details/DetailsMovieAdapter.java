package ua.r4mstein.moviedbdemo.modules.films.details;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.Genre;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.MovieDetailsModel;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.ProductionCompany;
import ua.r4mstein.moviedbdemo.data.models.response.movie_details.ProductionCountry;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class DetailsMovieAdapter extends RecyclerView.Adapter<DetailsMovieAdapter.DetailsMovieAdapterViewHolder> {

    private List<MovieDetailsModel> mMovieList = new ArrayList<>();
    private Context mContext;
    private DetailsMovieClickListener mDetailsMovieClickListener;

    public DetailsMovieAdapter(Context context) {
        mContext = context;
    }

    @Override
    public DetailsMovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DetailsMovieAdapterViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_details_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailsMovieAdapterViewHolder holder, int position) {
        MovieDetailsModel model = mMovieList.get(position);

        fillPoster(holder.ivPoster, model.getPosterPath());
        fillData(model, holder);

        holder.rvContainer.setOnLongClickListener(v -> {
            mDetailsMovieClickListener.moviesItemLongClicked(model.getId());
            return true;
        });
        holder.tvVote.setOnClickListener(v -> mDetailsMovieClickListener.ratingViewClicked(model.getId()));
        holder.tvVote.setOnLongClickListener(v -> {
            mDetailsMovieClickListener.ratingViewLongClicked(model.getId());
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    private void fillData(@NonNull MovieDetailsModel model, DetailsMovieAdapterViewHolder holder) {
        holder.tvTitle.setText(model.getTitle());

        fillTagline(model.getTagline(), holder);
        fillOverview(model.getOverview(), holder);
        fillReleaseDate(model.getReleaseDate(), holder);
        fillBudget(model.getBudget(), holder);
        fillRevenue(model.getRevenue(), holder);
        fillRuntime(model.getRuntime(), holder);
        fillGenres(model.getGenres(), holder);
        fillCompanies(model.getProductionCompanies(), holder);
        fillCountries(model.getProductionCountries(), holder);

        holder.tvVote.setText(String.valueOf(model.getVoteAverage()));
        holder.tvVoteCount.setText(String.valueOf(model.getVoteCount()));
    }

    private void fillCountries(List<ProductionCountry> data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty())
            holder.tvCountries.append(mContext.getResources().getText(R.string.error_movie_release_date) + "\n");
        else {
            for (ProductionCountry country : data) {
                holder.tvCountries.append(String.format("%s\n", country.getName()));
            }
        }
    }

    private void fillCompanies(List<ProductionCompany> data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty())
            holder.tvCompanies.append(mContext.getResources().getText(R.string.error_movie_release_date) + "\n");
        else {
            for (ProductionCompany company : data) {
                holder.tvCompanies.append(String.format("%s\n", company.getName()));
            }
        }
    }

    private void fillGenres(List<Genre> data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty())
            holder.tvGenres.append(mContext.getResources().getText(R.string.error_movie_release_date) + "\n");
        else {
            for (Genre genre : data) {
                holder.tvGenres.append(String.format("%s\n", genre.getName()));
            }
        }
    }

    private void fillRuntime(Long data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data == 0) holder.tvRuntime.setText(
                String.format("%s %s", "Runtime:", mContext.getResources().getText(R.string.error_movie_release_date)));
        else holder.tvRuntime.setText(String.format("%s %s %s", "Runtime:", String.valueOf(data), "minutes"));
    }

    private void fillRevenue(long data, DetailsMovieAdapterViewHolder holder) {
        if (data == 0) holder.tvRevenue.setText(
                String.format("%s %s", "Revenue:", mContext.getResources().getText(R.string.error_movie_release_date)));
        else holder.tvRevenue.setText(String.format("%s %s%s", "Revenue:", String.valueOf(data), "$"));
    }

    private void fillBudget(long data, DetailsMovieAdapterViewHolder holder) {
        if (data == 0) holder.tvBudget.setText(
                String.format("%s %s", "Budget:", mContext.getResources().getText(R.string.error_movie_release_date)));
        else holder.tvBudget.setText(String.format("%s %s%s", "Budget:", String.valueOf(data), "$"));
    }

    private void fillReleaseDate(String data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty()) holder.tvReleaseDate.setText(
                String.format("%s %s", "Release Date:", mContext.getResources().getText(R.string.error_movie_release_date)));
        else holder.tvReleaseDate.setText(String.format("%s %s", "Release Date:", data));
    }

    private void fillOverview(String data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty()) holder.tvOverview.setText(R.string.error_movie_overview);
        else holder.tvOverview.setText(data);
    }

    private void fillTagline(String data, DetailsMovieAdapterViewHolder holder) {
        if (data == null || data.isEmpty()) holder.tvTagline.setVisibility(View.GONE);
        else holder.tvTagline.setText(data);
    }

    private void fillPoster(ImageView imageView, String url) {
        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + url)
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(imageView);
    }

    public void addData(List<MovieDetailsModel> movies) {
        if (movies != null) {
            mMovieList.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public void setDetailsMovieClickListener(DetailsMovieClickListener detailsMovieClickListener) {
        mDetailsMovieClickListener = detailsMovieClickListener;
    }

    public class DetailsMovieAdapterViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rvContainer;
        private ImageView ivPoster;

        private TextView tvTitle, tvTagline, tvOverview, tvCompanies, tvCountries, tvGenres;
        private TextView tvReleaseDate, tvBudget, tvRevenue, tvVote, tvVoteCount, tvRuntime;

        public DetailsMovieAdapterViewHolder(View itemView) {
            super(itemView);

            rvContainer = (RelativeLayout) itemView.findViewById(R.id.rl_container_FDM);
            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster_FDM);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_FDM);
            tvTagline = (TextView) itemView.findViewById(R.id.tv_tagline_FDM);
            tvOverview = (TextView) itemView.findViewById(R.id.tv_overview_FDM);
            tvCompanies = (TextView) itemView.findViewById(R.id.tv_companies_FDM);
            tvCountries = (TextView) itemView.findViewById(R.id.tv_countries_FDM);
            tvGenres = (TextView) itemView.findViewById(R.id.tv_genres_FDM);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date_FDM);
            tvBudget = (TextView) itemView.findViewById(R.id.tv_budget_FDM);
            tvRevenue = (TextView) itemView.findViewById(R.id.tv_revenue_FDM);
            tvVote = (TextView) itemView.findViewById(R.id.tv_vote_FDM);
            tvVoteCount = (TextView) itemView.findViewById(R.id.tv_vote_count_FDM);
            tvRuntime = (TextView) itemView.findViewById(R.id.tv_runtime_FDM);
        }
    }
}
