package ua.r4mstein.moviedbdemo.modules.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.List;

import ua.r4mstein.moviedbdemo.R;
import ua.r4mstein.moviedbdemo.data.models.response.popular_people.KnownFor;
import ua.r4mstein.moviedbdemo.modules.detail.DetailActivity;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.KnownForClickListener;

import static ua.r4mstein.moviedbdemo.utills.Constants.DETAILS_MOVIE_FRAGMENT;

public class KnownForDialog extends DialogFragment {

    private ImageView ivOk;
    private RecyclerView mRecyclerView;

    private List<KnownFor> modelList;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return inflater.inflate(R.layout.dialog_known_for, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initVariables(view);
        initRecyclerView();
        getDialog().setCanceledOnTouchOutside(false);

        ivOk.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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

    public static KnownForDialog newInstance(List<KnownFor> list) {
        KnownForDialog dialog = new KnownForDialog();
        dialog.setModelList(list);

        return dialog;
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        KnownForDialogAdapter adapter = new KnownForDialogAdapter(getContext());
        adapter.setKnownForClickListener(getKnownForClickListener());
        mRecyclerView.setAdapter(adapter);
        adapter.addData(modelList);
    }

    @NonNull
    private KnownForClickListener getKnownForClickListener() {
        return movieId -> {
            Bundle bundle = new Bundle();
            bundle.putLong(DETAILS_MOVIE_FRAGMENT, movieId);
            Intent intent = new Intent(getContext(), DetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent, bundle);
        };
    }

    private void initVariables(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_DNF);
        ivOk = (ImageView) view.findViewById(R.id.iv_ok_DNF);
    }

    public void setModelList(List<KnownFor> modelList) {
        this.modelList = modelList;
    }
}
