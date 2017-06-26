package ua.r4mstein.moviedbdemo.modules.people.popular;

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
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.PopularPeopleItem;
import ua.r4mstein.moviedbdemo.utills.Constants;

public class PopularPeopleAdapter extends RecyclerView.Adapter<PopularPeopleAdapter.PopularPeopleViewHolder> {

    private List<PopularPeopleItem> mModelList = new ArrayList<>();
    private Context mContext;
    private PopularPeopleClickListener mPopularPeopleClickListener;

    public PopularPeopleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public PopularPeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularPeopleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_popular_people, parent, false));
    }

    @Override
    public void onBindViewHolder(PopularPeopleViewHolder holder, int position) {
        PopularPeopleItem model = mModelList.get(position);

        fillPoster(holder, model);
        fillData(holder, model);

        holder.itemView.setOnClickListener(v -> mPopularPeopleClickListener.popularPeopleItemClicked(model.getId()));
        holder.tvKnownFor.setOnClickListener(v -> mPopularPeopleClickListener.knownForClicked(model.getKnownFor()));
    }

    private void fillData(PopularPeopleViewHolder holder, PopularPeopleItem model) {
        holder.tvTitle.setText(model.getName());
        holder.tvPopularity.setText(String.format("Popularity: %s", String.valueOf(model.getPopularity())));
    }

    private void fillPoster(PopularPeopleViewHolder holder, PopularPeopleItem model) {
        Picasso.with(mContext)
                .load(Constants.IMAGE_BASE_URL + model.getProfilePath())
                .placeholder(R.drawable.main_logo)
                .error(R.drawable.main_logo)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return mModelList.size();
    }

    public void setData(List<PopularPeopleItem> list) {
        mModelList = list;
        notifyDataSetChanged();
    }

    public void addData(List<PopularPeopleItem> movies) {
        if (movies != null) {
            mModelList.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public void setPopularPeopleClickListener(PopularPeopleClickListener popularPeopleClickListener) {
        mPopularPeopleClickListener = popularPeopleClickListener;
    }

    public class PopularPeopleViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle;
        private TextView tvPopularity;
        private TextView tvKnownFor;

        public PopularPeopleViewHolder(View itemView) {
            super(itemView);

            ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster_IPP);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_IPP);
            tvPopularity = (TextView) itemView.findViewById(R.id.popularity_IPP);
            tvKnownFor = (TextView) itemView.findViewById(R.id.tv_known_IPP);
        }
    }
}
