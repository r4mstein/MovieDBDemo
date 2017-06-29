package ua.r4mstein.moviedbdemo.modules.lists.list_details;

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

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.Movie;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class ListDetailsAdapter extends RecyclerView.Adapter<ListDetailsAdapter.ListDetailsViewHolder> {

    private List<Movie> mMovieList = new ArrayList<>();
    private Context mContext;
    private ListDetailsClickListener mListDetailsClickListener;

    public ListDetailsAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ListDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListDetailsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_details, parent, false));
    }

    @Override
    public void onBindViewHolder(ListDetailsViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        fillData(holder, movie);
        fillPoster(holder, movie);

        holder.itemView.setOnClickListener(v -> mListDetailsClickListener.itemClicked(movie.getId()));
        holder.itemView.setOnLongClickListener(v -> {
            mListDetailsClickListener.itemLongClicked(movie.getTitle(), movie.getId());
            return true;
        });
        holder.tvVote.setOnClickListener(v -> mListDetailsClickListener.ratingViewClicked(movie.getId(), movie.getRating()));
        holder.tvVote.setOnLongClickListener(v -> {
            mListDetailsClickListener.ratingViewLongClicked(movie.getId());
            return true;
        });
    }

    private void fillPoster(ListDetailsViewHolder holder, Movie movie) {
        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(holder.ivPoster);
    }

    private void fillData(ListDetailsViewHolder holder, Movie movie) {
        holder.tvTitle.setText(movie.getOriginalTitle());

        if (movie.getOverview() != null && !movie.getOverview().isEmpty())
            holder.tvOverview.setText(movie.getOverview());
        else
            holder.tvOverview.setText(mContext.getResources().getText(R.string.error_movie_overview));

        if (movie.getReleaseDate() != null && !movie.getReleaseDate().isEmpty())
            holder.tvReleaseDate.setText(String.format("%s %s", "Release date: ", movie.getReleaseDate()));
        else holder.tvReleaseDate.setText(String.format("%s %s", "Release date: ",
                mContext.getResources().getText(R.string.error_movie_release_date)));

        if (movie.getVoteAverage() != null)
            holder.tvVote.setText(String.valueOf(movie.getVoteAverage()));
        else holder.tvVote.setText("0");

        if (movie.getVoteCount() != null)
            holder.tvVoteCount.setText(String.valueOf(movie.getVoteCount()));
        else holder.tvVoteCount.setText("0");
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setData(List<Movie> list) {
        mMovieList = list;
        notifyDataSetChanged();
    }

    public void setListDetailsClickListener(ListDetailsClickListener listDetailsClickListener) {
        mListDetailsClickListener = listDetailsClickListener;
    }

    public class ListDetailsViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvOverview;
        private TextView tvReleaseDate;
        private TextView tvVote;
        private TextView tvVoteCount;

        public ListDetailsViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster_ILD);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_ILD);
            tvOverview = (TextView) itemView.findViewById(R.id.tv_overview_ILD);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date_ILD);
            tvVote = (TextView) itemView.findViewById(R.id.tv_vote_ILD);
            tvVoteCount = (TextView) itemView.findViewById(R.id.tv_vote_count_ILD);
        }
    }
}
