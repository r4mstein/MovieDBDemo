package ua.r4mstein.moviedbdemo.modules.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.OnDialogButtonClickListener;

public class CreateDialog extends DialogFragment {

    public static final String DIALOG_TITLE = "dialog_title";

    private TextView tvTitle;
    private TextView tvPositive;
    private TextView tvNegative;

    private TextInputLayout tilName;
    private TextInputLayout tilDescription;

    private TextInputEditText tetName;
    private TextInputEditText tetDescription;

    private OnDialogButtonClickListener mClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                |WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        return inflater.inflate(R.layout.dialog_create_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        tvTitle.setText(getArguments().getString(DIALOG_TITLE));
        getDialog().setCanceledOnTouchOutside(false);

        tvPositive.setOnClickListener(v -> {
            String name = tetName.getText().toString().trim();
            String description = tetDescription.getText().toString().trim();

            if (!TextUtils.isEmpty(tetName.getText().toString())) {
                mClickListener.onPositiveClicked(name, description);
                dismiss();
            } else {
                tilName.setErrorEnabled(true);
                tilName.setError("Please enter name");
            }
        });

        tvNegative.setOnClickListener(v -> dismiss());
    }

    public static CreateDialog newInstance(String title) {
        CreateDialog dialog = new CreateDialog();

        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TITLE, title);
        dialog.setArguments(bundle);

        return dialog;
    }

    private void initVariables(View _rootView) {
        tvTitle = (TextView) _rootView.findViewById(R.id.tvTitle_DCL);
        tvPositive = (TextView) _rootView.findViewById(R.id.tvPositive_DCL);
        tvNegative = (TextView) _rootView.findViewById(R.id.tvNegative_DCL);

        tilName = (TextInputLayout) _rootView.findViewById(R.id.til_name_container_DCL);
        tilDescription = (TextInputLayout) _rootView.findViewById(R.id.til_description_container_DCL);

        tetName = (TextInputEditText) _rootView.findViewById(R.id.tiet_name_DCL);
        tetDescription = (TextInputEditText) _rootView.findViewById(R.id.tiet_description_DCL);
    }

    public void setClickListener(OnDialogButtonClickListener clickListener) {
        mClickListener = clickListener;
    }
}
