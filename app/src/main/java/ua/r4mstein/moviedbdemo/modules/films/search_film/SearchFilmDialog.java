package ua.r4mstein.moviedbdemo.modules.films.search_film;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ua.r4mstein.moviedbdemo.R;

public class SearchFilmDialog extends DialogFragment {

    public static final String DIALOG_TITLE = "dialog_title";

    private TextView tvTitle;
    private TextView tvPositive;
    private TextView tvNegative;

    private TextInputLayout tilSearchContainer;
    private TextInputEditText tetSearchRequest;

    private RecyclerView mRecyclerView;

    private OnSearchClickListener mClickListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color
                .TRANSPARENT));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                |WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return inflater.inflate(R.layout.dialog_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        tvTitle.setText(getArguments().getString(DIALOG_TITLE));
        getDialog().setCanceledOnTouchOutside(false);

        initButtons();
    }

    @Override
    public void onResume() {
        // Get existing layout params for the window
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Assign window properties to fill the parent
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

        super.onResume();
    }

    public static SearchFilmDialog newInstance(String title) {
        SearchFilmDialog dialog = new SearchFilmDialog();

        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, title);
        dialog.setArguments(bundle);

        return dialog;
    }

    private void initButtons() {
        tvPositive.setOnClickListener(v -> {
            String searchRequest = tetSearchRequest.getText().toString().trim();

            if (!TextUtils.isEmpty(tetSearchRequest.getText().toString())) {
                mClickListener.onPositiveClicked(searchRequest, mRecyclerView);
            } else {
                tilSearchContainer.setErrorEnabled(true);
                tilSearchContainer.setError("Please enter Search Request");
            }
        });

        tvNegative.setOnClickListener(v -> dismiss());
    }

    private void initVariables(View view) {
        tvTitle = (TextView) view.findViewById(R.id.tvTitle_DS);
        tvPositive = (TextView) view.findViewById(R.id.tvPositive_DS);
        tvNegative = (TextView) view.findViewById(R.id.tvNegative_DS);

        tilSearchContainer = (TextInputLayout) view.findViewById(R.id.til_search_container_DS);
        tetSearchRequest = (TextInputEditText) view.findViewById(R.id.tiet_search_request_DS);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_DS);
    }

    public void setClickListener(OnSearchClickListener clickListener) {
        mClickListener = clickListener;
    }
}
