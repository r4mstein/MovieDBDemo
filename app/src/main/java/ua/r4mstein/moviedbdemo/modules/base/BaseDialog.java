package ua.r4mstein.moviedbdemo.modules.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.r4mstein.moviedbdemo.R;

public abstract class BaseDialog extends DialogFragment {

    private int mContentResource = getLayoutResource();

    private CompositeDisposable mSubscriptions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup _container, Bundle _savedInstanceState) {
        super.onCreateView(_inflater, _container, _savedInstanceState);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = _inflater.inflate(R.layout.dialog_base_layout, _container, false);
        if (mContentResource != 0) {
            FrameLayout frameLayout = (FrameLayout) rootView.findViewById(R.id.content_container_BDL);
            frameLayout.removeAllViews();
            frameLayout.addView(_inflater.inflate(mContentResource, frameLayout, false));
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View _view, Bundle _savedInstanceState) {
        super.onViewCreated(_view, _savedInstanceState);
        setupViews(_view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected void addSubscription(Disposable _subscription) {
        mSubscriptions.remove(_subscription);
        mSubscriptions.add(_subscription);
    }

    protected abstract int getLayoutResource();

    protected abstract void setupViews(View _rootView);

    public void setIcon(@DrawableRes int icon) {
        new UnsupportedOperationException();
    }

    public void setTitle(@StringRes int _title) {
        new UnsupportedOperationException();
    }

    public void setMessage(String _title) {
        new UnsupportedOperationException();
    }

    public void setMessage(@StringRes int _message) {
        new UnsupportedOperationException();
    }

    public void setOnPositiveClickListener(View.OnClickListener _listener) {
        new UnsupportedOperationException();
    }

    public void setOnNegativeClickListener(View.OnClickListener _listener) {
        new UnsupportedOperationException();
    }


}
