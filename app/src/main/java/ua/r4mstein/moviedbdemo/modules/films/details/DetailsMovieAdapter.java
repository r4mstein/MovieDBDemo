package ua.r4mstein.moviedbdemo.modules.films.details;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

        fillData(model, holder);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    private void fillData(@NonNull MovieDetailsModel movieDetailsModel, DetailsMovieAdapterViewHolder holder) {
        fillPoster(holder.ivPoster, movieDetailsModel.getPosterPath());

        holder.tvTitle.setText(movieDetailsModel.getTitle());
        holder.tvTagline.setText(movieDetailsModel.getTagline());
        holder.tvOverview.setText(movieDetailsModel.getOverview());
        holder.tvReleaseDate.setText(String.format("%s %s", "Release Date:", movieDetailsModel.getReleaseDate()));
        holder.tvBudget.setText(String.format("%s %s%s", "Budget:", String.valueOf(movieDetailsModel.getBudget()), "$"));
        holder.tvRevenue.setText(String.format("%s %s%s", "Revenue:", String.valueOf(movieDetailsModel.getRevenue()), "$"));
        holder.tvRuntime.setText(String.format("%s %s %s", "Runtime:", String.valueOf(movieDetailsModel.getRuntime()), "minutes"));
        holder.tvVote.setText(String.valueOf(movieDetailsModel.getVoteAverage()));
        holder.tvVoteCount.setText(String.valueOf(movieDetailsModel.getVoteCount()));

        for (Genre genre : movieDetailsModel.getGenres()) {
            holder.tvGenres.append(String.format("%s\n", genre.getName()));
        }

        for (ProductionCompany company: movieDetailsModel.getProductionCompanies()) {
            holder.tvCompanies.append(String.format("%s\n", company.getName()));
        }

        for (ProductionCountry country : movieDetailsModel.getProductionCountries()) {
            holder.tvCountries.append(String.format("%s\n", country.getName()));
        }
    }

    public void addData(List<MovieDetailsModel> movies) {
        if (movies != null) {
            mMovieList.addAll(movies);
            notifyDataSetChanged();
        }
    }

    private void fillPoster(ImageView imageView, String url) {
        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + url)
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(imageView);
    }

    public class DetailsMovieAdapterViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;

        private TextView tvTitle, tvTagline, tvOverview, tvCompanies, tvCountries, tvGenres;
        private TextView tvReleaseDate, tvBudget, tvRevenue, tvVote, tvVoteCount, tvRuntime;

        public DetailsMovieAdapterViewHolder(View itemView) {
            super(itemView);

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
