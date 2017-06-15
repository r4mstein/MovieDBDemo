package ua.r4mstein.moviedbdemo.modules.lists;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.GetListsModel;

public class GetListsAdapter extends RecyclerView.Adapter<GetListsAdapter.GetListsViewHolder> {

    private List<GetListsModel.Result> mModels = new ArrayList<>();
    private GetListsActionListener mActionListener;

    public GetListsAdapter() {

    }

    @Override
    public GetListsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GetListsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_get_lists, parent, false));
    }

    @Override
    public void onBindViewHolder(GetListsViewHolder holder, int position) {
        GetListsModel.Result model = mModels.get(position);

        holder.tvName.setText(model.getName());
        holder.tvCount.setText(String.format("%s %s", "Items count: ", String.valueOf(model.getItemCount())));

        holder.cvContainer.setOnClickListener(v -> mActionListener.getListsItemClicked(model.getId()));
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    public void setData(List<GetListsModel.Result> list) {
        mModels = list;
        notifyDataSetChanged();
    }

    public void addData(List<GetListsModel.Result> movies) {
        if (movies != null) {
            mModels.addAll(movies);
            notifyDataSetChanged();
        }
    }

    public void setActionListener(GetListsActionListener actionListener) {
        mActionListener = actionListener;
    }

    public class GetListsViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvCount;
        private CardView cvContainer;

        public GetListsViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name_IGL);
            tvCount = (TextView) itemView.findViewById(R.id.tv_count_IGL);
            cvContainer = (CardView) itemView.findViewById(R.id.cv_container_IGL);
        }
    }
}
