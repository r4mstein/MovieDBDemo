package ua.r4mstein.moviedbdemo.modules.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import ua.r4mstein.moviedbdemo.modules.dialog.ChooseActionDialog;
import ua.r4mstein.moviedbdemo.modules.dialog.DialogRating;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.ChooseActionClickListener;
import ua.r4mstein.moviedbdemo.modules.dialog.listeners.DialogRatingClickListener;
import ua.r4mstein.moviedbdemo.utills.Logger;

import static ua.r4mstein.moviedbdemo.utills.Constants.CHOOSE_ACTION_EXCEPTION;
import static ua.r4mstein.moviedbdemo.utills.Constants.DIALOG_RATING_EXCEPTION;


public abstract class BaseFragment<P extends BaseFragmentPresenter> extends Fragment
        implements FragmentView {

    private View rootView;
    private P mPresenter;
    private BaseActivity mBaseActivity;
    private MenuController mMenuController;

    protected abstract int getTitle();

    protected String getStringTitle(){
        return null;
    }

    protected abstract int getLayoutResource();

    protected abstract P initPresenter();

    protected abstract void findUI(View rootView);

    protected abstract void setupUI();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = inflater.inflate(getLayoutResource(), container, false);
        findUI(rootView);

        mBaseActivity = (BaseActivity) getActivity();

        if (mPresenter == null) {
            mPresenter = initPresenter();
            setupUI();
            mPresenter.setRouter(mBaseActivity.getPresenter().getRouter());
            if (getPresenter() == null)
                new ClassCastException("Presenter is not created + " + this.getClass().getName());
        }
        getPresenter().bindView(this);
        getPresenter().onCreateView(savedInstanceState);
        mMenuController = initMenuController();
        return rootView;
    }

    protected MenuController initMenuController() {
        return null;
    }

    protected MenuController getMenuController() {
        return mMenuController;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (mMenuController != null) {
            mMenuController.onCreateOptionsMenu(menu, inflater);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public Bundle getViewArguments() {
        return getArguments();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.logStackTrace();
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Logger.logStackTrace();
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                getPresenter().onViewCreated();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onStop() {
        getPresenter().onStop();
        super.onStop();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) getPresenter().onPause();
        else getPresenter().onResume();
    }

    @Override
    public void onDestroyView() {
        getPresenter().onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getPresenter().onActivityResult(requestCode, resultCode, data);
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public Fragment asFragment() {
        return this;
    }

    @Override
    public Point getDisplaySize() {
        return mBaseActivity.getDisplaySize();
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public FragmentManager getFragmManager() {
        return getFragmentManager();
    }

    @Override
    public Resources getAppResources() {
        return getResources();
    }

    @Override
    public ChooseActionClickListener getChooseActionClickListener(long movieId, ChooseActionDialog dialog) {
        throw new IllegalArgumentException(CHOOSE_ACTION_EXCEPTION);
    }

    @Override
    public DialogRatingClickListener getDialogRatingClickListener(long movieId, DialogRating dialogRating) {
        throw new IllegalArgumentException(DIALOG_RATING_EXCEPTION);
    }
}
