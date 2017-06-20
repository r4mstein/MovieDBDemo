package ua.r4mstein.moviedbdemo.modules.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;

public class ChooseActionDialog extends DialogFragment {

    private TextView tvFavorite;
    private TextView tvWatchList;

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

        tvFavorite.setOnClickListener(v -> mChooseActionClickListener.favoriteClicked());
        tvWatchList.setOnClickListener(v -> mChooseActionClickListener.watchlistClicked());
    }

    private void initVariables(View view) {
        tvFavorite = (TextView) view.findViewById(R.id.tv_favorite_DCA);
        tvWatchList = (TextView) view.findViewById(R.id.tv_watchlist_DCA);
    }

    public void setChooseActionClickListener(ChooseActionClickListener chooseActionClickListener) {
        mChooseActionClickListener = chooseActionClickListener;
    }
}
