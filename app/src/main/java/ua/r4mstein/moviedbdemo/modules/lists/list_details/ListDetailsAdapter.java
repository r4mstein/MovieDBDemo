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

        holder.tvTitle.setText(movie.getOriginalTitle());
        holder.tvOverview.setText(movie.getOverview());
        holder.tvReleaseDate.setText(String.format("%s %s", "Release date: ", movie.getReleaseDate()));
        holder.tvVote.setText(String.valueOf(movie.getVoteAverage()));
        holder.tvVoteCount.setText(String.valueOf(movie.getVoteCount()));

        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void setData(List<Movie> list) {
        mMovieList = list;
        notifyDataSetChanged();
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
