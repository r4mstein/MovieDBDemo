package ua.r4mstein.moviedbdemo.modules.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;

public class ChooseActionDialog extends DialogFragment {

    public static final String FAVORITE_VISIBILITY = "favorite_visibility";
    public static final String WATCHLIST_VISIBILITY = "watchlist_visibility";
    public static final String REMOVE_FAVORITE_VISIBILITY = "remove_favorite_visibility";

    private TextView tvFavorite;
    private TextView tvWatchList;
    private TextView tvRemoveFromFavorite;

    private LinearLayout llFavorite;
    private LinearLayout llWatchList;
    private LinearLayout llRemoveFromFavorite;

    private ChooseActionClickListener mChooseActionClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return inflater.inflate(R.layout.dialog_choose_action, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);

        setVisibilityForContainers(getArguments().getInt(FAVORITE_VISIBILITY),
                getArguments().getInt(WATCHLIST_VISIBILITY),
                getArguments().getInt(REMOVE_FAVORITE_VISIBILITY));

        tvFavorite.setOnClickListener(v -> mChooseActionClickListener.favoriteClicked());
        tvWatchList.setOnClickListener(v -> mChooseActionClickListener.watchlistClicked());
        tvRemoveFromFavorite.setOnClickListener(v -> mChooseActionClickListener.removeFromFavoriteClicked());
    }

    public static ChooseActionDialog newInstance(int favorite, int watchlist, int removeFavorite) {
        ChooseActionDialog dialog = new ChooseActionDialog();

        Bundle bundle = new Bundle();
        bundle.putInt(FAVORITE_VISIBILITY, favorite);
        bundle.putInt(WATCHLIST_VISIBILITY, watchlist);
        bundle.putInt(REMOVE_FAVORITE_VISIBILITY, removeFavorite);
        dialog.setArguments(bundle);

        return dialog;
    }

    private void initVariables(View view) {
        tvFavorite = (TextView) view.findViewById(R.id.tv_favorite_DCA);
        tvWatchList = (TextView) view.findViewById(R.id.tv_watchlist_DCA);
        tvRemoveFromFavorite = (TextView) view.findViewById(R.id.tv_remove_favorite_DCA);

        llFavorite = (LinearLayout) view.findViewById(R.id.ll_favorite_container_DCA);
        llWatchList = (LinearLayout) view.findViewById(R.id.ll_watchlist_container_DCA);
        llRemoveFromFavorite = (LinearLayout) view.findViewById(R.id.ll_remove_favorite_container_DCA);
    }

    private void setVisibilityForContainers(int favorite, int watchlist, int removeFavorite) {
        llFavorite.setVisibility(favorite);
        llWatchList.setVisibility(watchlist);
        llRemoveFromFavorite.setVisibility(removeFavorite);
    }

    public void setChooseActionClickListener(ChooseActionClickListener chooseActionClickListener) {
        mChooseActionClickListener = chooseActionClickListener;
    }
}
