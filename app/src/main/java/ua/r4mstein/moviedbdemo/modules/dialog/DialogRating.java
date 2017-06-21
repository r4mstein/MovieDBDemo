package ua.r4mstein.moviedbdemo.modules.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.DialogRatingClickListener;

public class DialogRating extends DialogFragment {

    private TextView btnPositive;
    private SimpleRatingBar mSimpleRatingBar;

    private DialogRatingClickListener mDialogRatingClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return inflater.inflate(R.layout.dialog_rating, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);

        mSimpleRatingBar.setOnRatingBarChangeListener((simpleRatingBar, rating, fromUser) -> mDialogRatingClickListener.ratingChanged(rating));
    }

    private void initVariables(View view) {
        btnPositive = (TextView) view.findViewById(R.id.btnPositive_DR);
        mSimpleRatingBar = (SimpleRatingBar) view.findViewById(R.id.rating_bar_DR);
    }

    public void setDialogRatingClickListener(DialogRatingClickListener dialogRatingClickListener) {
        mDialogRatingClickListener = dialogRatingClickListener;
    }
}
