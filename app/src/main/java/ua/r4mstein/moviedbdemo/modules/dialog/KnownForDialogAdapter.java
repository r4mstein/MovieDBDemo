package ua.r4mstein.moviedbdemo.modules.dialog;

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
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class KnownForDialogAdapter extends RecyclerView.Adapter<KnownForDialogAdapter.KnownForDialogViewHolder> {

    private List<KnownFor> mModelList = new ArrayList<>();
    private Context mContext;

    public KnownForDialogAdapter(Context context) {
        mContext = context;
    }

    @Override
    public KnownForDialogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KnownForDialogViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_by_genre, parent, false));
    }

    @Override
    public void onBindViewHolder(KnownForDialogViewHolder holder, int position) {
        KnownFor model = mModelList.get(position);

        fillPoster(holder, model);
        fillData(holder, model);
    }

    private void fillData(KnownForDialogViewHolder holder, KnownFor model) {
        holder.tvTitle.setText(model.getOriginalTitle());

        if (model.getOverview() != null && !model.getOverview().isEmpty())
            holder.tvOverview.setText(model.getOverview());
        else holder.tvOverview.setText(mContext.getResources().getText(R.string.error_movie_overview));

        if (model.getReleaseDate() != null && !model.getReleaseDate().isEmpty())
            holder.tvReleaseDate.setText(String.format("%s %s", "Release date: ", model.getReleaseDate()));
        else holder.tvReleaseDate.setText(String.format("%s %s", "Release date: ",
                mContext.getResources().getText(R.string.error_movie_release_date)));

        if (model.getVoteAverage() != null) holder.tvVote.setText(String.valueOf(model.getVoteAverage()));
        else holder.tvVote.setText("0");

        if (model.getVoteCount() != null) holder.tvVoteCount.setText(String.valueOf(model.getVoteCount()));
        else holder.tvVoteCount.setText("0");
    }

    private void fillPoster(KnownForDialogViewHolder holder, KnownFor model) {
        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + model.getPosterPath())
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    public void addData(List<KnownFor> models) {
        if (models != null) {
            mModelList.addAll(models);
            notifyDataSetChanged();
        }
    }

    public class KnownForDialogViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvOverview;
        private TextView tvReleaseDate;
        private TextView tvVote;
        private TextView tvVoteCount;

        public KnownForDialogViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster_IMBG);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_IMBG);
            tvOverview = (TextView) itemView.findViewById(R.id.tv_overview_IMBG);
            tvReleaseDate = (TextView) itemView.findViewById(R.id.tv_release_date_IMBG);
            tvVote = (TextView) itemView.findViewById(R.id.tv_vote_IMBG);
            tvVoteCount = (TextView) itemView.findViewById(R.id.tv_vote_count_IMBG);
        }
    }
}
